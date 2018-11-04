package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.aspect.AbstractAspect
import com.lpcoder.agile.base.bean.container.support.aspect.BeanAspectResolver
import com.lpcoder.agile.base.bean.container.support.definition.BeanConstructorResolver
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueConverter
import com.lpcoder.agile.base.bean.container.support.exception.BeanCreationException
import com.lpcoder.agile.base.bean.container.support.exception.BeanDefinitionException
import com.lpcoder.agile.base.bean.container.support.parser.BeanParser
import com.lpcoder.agile.base.bean.container.support.parser.SupportedFileTypeEnum
import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.core.resource.ClassPathResource
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.ClassUtil
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil.getFromMapForcibly
import org.apache.commons.beanutils.BeanUtils
import org.slf4j.LoggerFactory

class DefaultBeanContainer(private val resource: Resource,
                           private val parser: BeanParser,
                           private val classLoader: ClassLoader)
    : BeanContainer {

    private val beanDefinitions = mutableSetOf<BeanDefinition>()
    private val aspects = mutableSetOf<AbstractAspect>()

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()
    private val beanClassMap = mutableMapOf<String, Class<*>>()
    private val singletonObjMap = mutableMapOf<String, Any>()

    private val beanConstructorResolver = BeanConstructorResolver(this)
    private val beanAspectResolver = BeanAspectResolver(this)

    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        initContainer()
    }

    constructor(configPath: String) : this(ClassPathResource(configPath))
    constructor(resource: Resource) : this(
            resource,
            SupportedFileTypeEnum.getBySuffix(resource.getFileSuffix()).parser,
            ClassUtil.getDefaultClassLoader())

    private fun initContainer() {
        try {
            beanDefinitions.addAll(parser.parseBeanDefinition(resource))
            aspects.addAll(parser.parseAspect(resource))

            beanDefinitions.stream().forEach {
                beanDefinitionMap[it.id] = it
                beanClassMap[it.id] = classLoader.loadClass(it.beanClassName)
            }
            beanDefinitions.stream().filter(BeanDefinition::isSingleton)
                    .filter { !singletonObjMap.containsKey(it.id) }.forEach {
                        singletonObjMap[it.id] = createBean(it.id)
                    }
            printInitEndLog()
        } catch (e: BeanCreationException) {
            throw e
        } catch (e: InstantiationException) {
            throw BeanCreationException("unable to instantiate singleton bean: ${e.message}", e)
        } catch (e: CheckException) {
            throw BeanDefinitionException(e.desc, e)
        } catch (e: ClassNotFoundException) {
            throw BeanDefinitionException("not found class " + (e.message ?: ""), e)
        } catch (e: Exception) {
            throw BeanDefinitionException(e.message ?: "", e)
        }
    }

    override fun getBean(beanId: String): Any {
        if (getBeanDefinition(beanId).isSingleton) {
            var bean = singletonObjMap[beanId]
            if (bean == null) {
                bean = createBean(beanId)
                singletonObjMap[beanId] = bean
            }
            return bean
        }
        try {
            return createBean(beanId)
        } catch (e: Exception) {
            throw BeanCreationException("create bean for '$beanId' failed. ${e.message ?: ""}", e)
        }
    }

    override fun getBeanDefinition(beanId: String): BeanDefinition =
            getFromMapForcibly(beanDefinitionMap, beanId, "beanDefinitionMap")

    override fun getBeanClass(beanId: String): Class<*> =
            getFromMapForcibly(beanClassMap, beanId, "beanClassMap")

    override fun getBeanClassLoader(): ClassLoader = classLoader

    override fun getBeanDefinitions(): Set<BeanDefinition> = beanDefinitions.toSet()

    override fun getAspects(): Set<AbstractAspect> = aspects.toSet()


    private fun createBean(beanId: String): Any {
        val bean = instantiateBean(beanId)
        populateBeanProperty(bean, beanId)
        return bean
    }

    private fun instantiateBean(beanId: String): Any {
        val constructorHasArgs = beanDefinitionMap[beanId]!!.constructorArgs.isNotEmpty()
        val needAdvice = CollectionUtil.isNotEmpty(beanAspectResolver.beanIdToAspectSetMap[beanId])
        return when {
            constructorHasArgs && needAdvice -> {
                val constructorArgTypes = beanConstructorResolver.getConstructor(beanId).parameterTypes
                val constructorArgs = beanConstructorResolver.getConstructorArgs(beanId)
                beanAspectResolver.createProxy(constructorArgTypes, constructorArgs, beanId)
            }
            constructorHasArgs && !needAdvice ->
                beanConstructorResolver.newInstanceByAutoWireConstructor(beanId)
            !constructorHasArgs && needAdvice ->
                beanAspectResolver.createProxyByNoArgumentConstructor(beanId)
            !constructorHasArgs && !needAdvice ->
                getFromMapForcibly(beanClassMap, beanId, "beanClassMap").newInstance()
            else -> throw RuntimeException("impossible condition happened")
        }
    }

    private fun populateBeanProperty(bean: Any, beanId: String) {
        val beanDefinition = getFromMapForcibly(beanDefinitionMap, beanId, "beanDefinitionMap")
        val beanProperties = beanDefinition.properties
        if (CollectionUtil.isEmpty(beanProperties)) return
        val converter = BeanPropertyValueConverter(this)
        beanProperties.forEach {
            BeanUtils.setProperty(bean, it.name, converter.convert(it.value))
        }
    }

    private fun printInitEndLog() {
        logger.info("beanContainer init end")
        logger.info("beanDefinitionMap:$beanDefinitionMap")
        logger.info("beanClassMap:$beanClassMap")
        logger.info("singletonObjMap:$singletonObjMap")
    }

}