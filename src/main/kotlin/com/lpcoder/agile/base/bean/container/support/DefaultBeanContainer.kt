package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
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
import java.util.stream.Collectors

class DefaultBeanContainer(private val resource: Resource,
                           private var parser: BeanDefinitionParser? = null)
    : BeanContainer {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()
    private val beanClassMap = mutableMapOf<String, Class<*>>()
    private val singletonObjMap = mutableMapOf<String, Any>()

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
            beanDefinitions.stream().filter(BeanDefinition::isSingleton).forEach {
                singletonObjMap[it.id] = createBean(it.id)
            }
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

    override fun getBeanDefinition(beanId: String): BeanDefinition? {
        return beanDefinitionMap[beanId]
    }

    override fun getBean(beanId: String): Any? {
        val beanDefinition = beanDefinitionMap[beanId]
        beanDefinition ?: throw BeanCreationException("not found beanDefinition of $beanId")
        if (beanDefinition.isSingleton) {
            return singletonObjMap[beanId]
        }
        return createBean(beanId)
    }

    private fun createBean(beanId: String): Any {
        try {
            if (!beanDefinitionMap.keys.contains(beanId)) {
                throw BeanCreationException("not found beanId: $beanId")
            }
            return beanClassMap[beanId]!!.newInstance()
        } catch (e: Exception) {
            throw BeanCreationException("create bean for ${beanDefinitionMap[beanId]?.beanClassName} failed", e)
        }
    }

}