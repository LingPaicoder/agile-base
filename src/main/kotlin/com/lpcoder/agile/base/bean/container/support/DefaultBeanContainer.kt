package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyConverter
import com.lpcoder.agile.base.bean.container.support.exception.BeanCreationException
import com.lpcoder.agile.base.bean.container.support.exception.BeanDefinitionException
import com.lpcoder.agile.base.bean.container.support.parser.BeanDefinitionParser
import com.lpcoder.agile.base.bean.container.support.parser.SupportedFileTypeEnum
import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.CollRuler.beNotContainsDup
import com.lpcoder.agile.base.core.resource.ClassPathResource
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.ClassUtil
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil
import com.lpcoder.agile.base.util.MapUtil.getFromMapForcibly
import org.apache.commons.beanutils.BeanUtils
import org.slf4j.LoggerFactory
import java.util.stream.Collectors

class DefaultBeanContainer(private val resource: Resource,
                           private var parser: BeanDefinitionParser? = null)
    : BeanContainer {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()
    private val beanClassMap = mutableMapOf<String, Class<*>>()
    private val singletonObjMap = mutableMapOf<String, Any>()

    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        initContainer()
    }

    constructor(configPath: String) : this(ClassPathResource(configPath))

    private fun initContainer() {
        try {
            parser = parser ?:
                    SupportedFileTypeEnum.getBySuffix(resource.getFileSuffix()).parser
            val beanDefinitions = parser!!.parse(resource)
            val beanIds = beanDefinitions.stream()
                    .map(BeanDefinition::id)
                    .collect(Collectors.toList())
            beanIds alias "beanIds" must beNotContainsDup

            beanDefinitions.stream().forEach {
                beanDefinitionMap[it.id] = it
                beanClassMap[it.id] = ClassUtil.getDefaultClassLoader().loadClass(it.beanClassName)
            }
            beanDefinitions.stream().filter(BeanDefinition::isSingleton)
                    .filter { !singletonObjMap.containsKey(it.id) }.forEach {
                singletonObjMap[it.id] = createBean(it.id)
            }
            printInitEndLog()
        } catch (e: BeanCreationException) {
            throw e
        } catch (e: CheckException) {
            throw BeanDefinitionException(e.desc, e)
        } catch (e: ClassNotFoundException) {
            throw BeanDefinitionException("not found class " + (e.message ?: ""), e)
        } catch (e: Exception) {
            throw BeanDefinitionException(e.message ?: "", e)
        }
    }

    override fun getBeanDefinition(beanId: String): BeanDefinition =
            getFromMapForcibly(beanDefinitionMap, beanId, "beanDefinitionMap")

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

    private fun createBean(beanId: String): Any {
        val bean = instantiateBean(beanId)
        populateBeanProperty(bean, beanId)
        return bean
    }

    private fun instantiateBean(beanId: String): Any {
        return if (beanDefinitionMap[beanId]!!.constructorArgs.isNotEmpty()) {
            Any()
        } else {
            getFromMapForcibly(beanClassMap, beanId, "beanClassMap").newInstance()
        }
    }

    private fun populateBeanProperty(bean: Any, beanId: String) {
        val beanDefinition = getFromMapForcibly(beanDefinitionMap, beanId, "beanDefinitionMap")
        val beanProperties = beanDefinition.properties
        if (CollectionUtil.isEmpty(beanProperties)) return
        val converter = BeanPropertyConverter(this)
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