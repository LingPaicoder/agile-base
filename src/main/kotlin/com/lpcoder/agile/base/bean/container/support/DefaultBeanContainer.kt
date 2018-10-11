package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.bean.exception.BeanCreationException
import com.lpcoder.agile.base.bean.parser.BeanDefinitionParser
import com.lpcoder.agile.base.bean.parser.SupportedFileTypeEnum
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.CollRuler.beNotContainsDup
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.ClassUtil
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors

class DefaultBeanContainer(resource: Resource,
                           private var parser: BeanDefinitionParser? = null)
    : BeanContainer {

    private val beanDefinitionMap: Map<String, BeanDefinition>
    private val beanClassMap: Map<String, Class<*>>
    private val singletonObjMap: Map<String, Any>

    init {
        parser = parser ?:
                SupportedFileTypeEnum.getBySuffix(resource.getFileSuffix()).parser
        val beanDefinitions = parser!!.parse(resource)
        val beanIds = beanDefinitions.stream()
                .map(BeanDefinition::id)
                .collect(Collectors.toList())
        beanIds alias "beanIds" must beNotContainsDup

        beanDefinitionMap = ConcurrentHashMap(64)
        beanClassMap = ConcurrentHashMap(64)
        beanDefinitions.stream().forEach {
            beanDefinitionMap[it.id] = it
            beanClassMap[it.id] = ClassUtil.getDefaultClassLoader().loadClass(it.beanClassName)
        }

        singletonObjMap = ConcurrentHashMap(64)
        beanDefinitions.stream()
                .filter(BeanDefinition::isSingleton)
                .forEach { singletonObjMap[it.id] = createBean(it) }
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
        return createBean(beanDefinition)
    }

    private fun createBean(beanDefinition: BeanDefinition): Any {
        try {
            return beanClassMap[beanDefinition.id]!!.newInstance()
        } catch (e: Exception) {
            throw BeanCreationException("create bean for ${beanDefinition.beanClassName} failed", e)
        }
    }

}