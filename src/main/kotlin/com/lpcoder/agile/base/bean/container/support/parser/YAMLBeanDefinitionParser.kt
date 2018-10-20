package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.definition.BeanConstructorArg
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.bean.container.support.definition.BeanProperty
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValue
import com.lpcoder.agile.base.bean.container.support.definition.support.RuntimeBeanReferenceValue
import com.lpcoder.agile.base.bean.container.support.definition.support.TypedStringValue
import com.lpcoder.agile.base.bean.container.support.exception.BeanDefinitionException
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNotNull
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.StringUtil
import org.dom4j.Element
import org.yaml.snakeyaml.Yaml

class YAMLBeanDefinitionParser : BeanDefinitionParser {

    override fun parse(source: Resource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull

        @Suppress("UNCHECKED_CAST")
        return source.getInputStream().use {
            Yaml().load(it) as Map<String, List<Map<String, Any>>>
        }[beansAttr]!!.map { element ->
            val id = element[idAttr] as String
            val clazz = element[classAttr] as String
            val isSingleton = (element[isSingletonAttr] ?: true) as Boolean
            val definition = BeanDefinition(id, clazz, isSingleton)
            parseConstructorArgs(element, definition)
            parseProperties(element, definition)
            return@map definition
        }
    }

    private fun parseConstructorArgs(element: Map<String, Any>, definition: BeanDefinition) {
        if (null == element[constructorArgElement]) return
        @Suppress("UNCHECKED_CAST")
        val constructorArgs = element[constructorArgElement] as List<Map<String, Any>>
        constructorArgs.forEach {
            val indexStr = it[indexAttr] as String?
            if (indexStr.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'index' attribute")
            }
            if (StringUtil.isDigit(indexStr)) {
                throw BeanDefinitionException("The 'index' attribute of Tag 'constructor-arg' must be digit")
            }
            val index = indexStr!!.toInt()
            val type = it[typeAttr] as String?
            if (type.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'type' attribute")
            }
            val constructorArgValue = beanConstructorArgValueOf(it, index)
            val constructorArg = BeanConstructorArg(index, type!!, constructorArgValue)
            definition.constructorArgs.add(constructorArg)
        }
    }

    private fun beanConstructorArgValueOf(propElement: Map<String, Any>, index: Int) =
            beanValueOf(propElement, "<constructor-arg> element for index '$index'")

    private fun parseProperties(element: Map<String, Any>, definition: BeanDefinition) {
        if (null == element[propertyElement]) return
        @Suppress("UNCHECKED_CAST")
        val properties = element[propertyElement] as List<Map<String, Any>>
        properties.forEach {
            val propertyName = it[nameAttr] as String?
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
        val isRefAttr = propElement[refAttr] != null
        val isValueAttr = propElement[valueAttr] != null
        return when {
            isRefAttr -> {
                val refName = StringUtil.getString(propElement[refAttr])
                if (refName.isBlank()) {
                    throw BeanDefinitionException("$elementDesc contains empty 'ref' attribute")
                }
                RuntimeBeanReferenceValue(refName)
            }
            isValueAttr -> {
                TypedStringValue(StringUtil.getString(propElement[valueAttr]))
            }
            else -> throw BeanDefinitionException("$elementDesc must specify a ref or value")
        }
    }

}