package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.aspect.AbstractAspect
import com.lpcoder.agile.base.bean.container.support.definition.*
import com.lpcoder.agile.base.bean.container.support.exception.BeanDefinitionException
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.MapUtil
import com.lpcoder.agile.base.util.StringUtil
import org.yaml.snakeyaml.Yaml

class YAMLBeanParser : BeanParser {

    override fun parseBeanDefinition(resource: Resource): Set<BeanDefinition> {
        checkResource(resource)
        val containerInfo = getContainerInfo(resource)
        val definitions = mutableSetOf<BeanDefinition>()
        parseAutoScans(containerInfo, definitions)
        parseBeans(containerInfo, definitions)
        return definitions
    }

    override fun parseAspect(resource: Resource): Set<AbstractAspect<*, *>> {
        checkResource(resource)
        val containerInfo = getContainerInfo(resource)
        val packages = getPackages(containerInfo) ?: return emptySet()
        return scanAspect(packages)
    }

    private fun parseAutoScans(container: Map<String, List<Map<String, Any>>>, definitions: MutableSet<BeanDefinition>) {
        val packages = getPackages(container) ?: return
        scanBeanDefinition(packages).forEach { definitions.add(it) }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getContainerInfo(resource: Resource) =
            MapUtil.getFromMapForcibly(resource.getInputStream().use {
                Yaml().load(it) as Map<String, Map<String, List<Map<String, Any>>>>
            }, containerKey, "containerConfig")

    private fun getPackages(container: Map<String, List<Map<String, Any>>>) =
            container[autoScansKey]?.map { it[packageKey] as String }?.toList()

    private fun parseBeans(container: Map<String, List<Map<String, Any>>>, definitions: MutableSet<BeanDefinition>) {
        container[beansKey]?.map { element ->
            val id = element[idKey] as String
            val clazz = element[classKey] as String
            val isSingleton = (element[isSingletonKey] ?: true) as Boolean
            val definition = BeanDefinition(id, clazz, isSingleton)
            parseConstructorArgs(element, definition)
            parseProperties(element, definition)
            return@map definition
        }?.forEach { definitions.add(it) }
    }

    private fun parseConstructorArgs(element: Map<String, Any>, definition: BeanDefinition) {
        if (null == element[constructorArgKey]) return
        @Suppress("UNCHECKED_CAST")
        val constructorArgs = element[constructorArgKey] as List<Map<String, Any>>
        constructorArgs.forEach {
            val indexStr = it[indexKey].toString()
            if (indexStr.isBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'index' attribute")
            }
            if (!StringUtil.isDigit(indexStr)) {
                throw BeanDefinitionException("The 'index' attribute of Tag 'constructor-arg' must be digit")
            }
            val index = indexStr.toInt()
            val type = it[typeKey] as String?
            if (type.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'type' attribute")
            }
            val constructorArgValue = beanConstructorArgValueOf(it, index)
            val constructorArg = BeanConstructorArg(index, type!!, constructorArgValue)
            definition.constructorArgs.add(constructorArg)
        }
        definition.constructorArgs.sortBy { it.index }
    }

    private fun beanConstructorArgValueOf(propElement: Map<String, Any>, index: Int) =
            beanValueOf(propElement, "<constructor-arg> element for index '$index'")

    private fun parseProperties(element: Map<String, Any>, definition: BeanDefinition) {
        if (null == element[propertyKey]) return
        @Suppress("UNCHECKED_CAST")
        val properties = element[propertyKey] as List<Map<String, Any>>
        properties.forEach {
            val propertyName = it[nameKey] as String?
            if (propertyName.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'property' must have a 'name' attribute")
            }
            val propertyValue = beanPropertyValueOf(it, propertyName!!)
            val property = BeanProperty(propertyName, propertyValue)
            definition.properties.add(property)
        }
    }

    private fun beanPropertyValueOf(propElement: Map<String, Any>, propertyName: String) =
            beanValueOf(propElement, "<property> element for property '$propertyName'")

    private fun beanValueOf(propElement: Map<String, Any>, elementDesc: String): BeanPropertyValue {
        val isRefAttr = propElement[refKey] != null
        val isValueAttr = propElement[valueKey] != null
        return when {
            isRefAttr -> {
                val refName = StringUtil.getString(propElement[refKey])
                if (refName.isBlank()) {
                    throw BeanDefinitionException("$elementDesc contains empty 'ref' attribute")
                }
                BeanPropertyValue(refName, BeanPropertyValueType.RUNTIME_BEAN_REFERENCE_TYPE)
            }
            isValueAttr -> {
                BeanPropertyValue(StringUtil.getString(propElement[valueKey]), BeanPropertyValueType.BASIC_TYPE)
            }
            else -> throw BeanDefinitionException("$elementDesc must specify a ref or value")
        }
    }

}