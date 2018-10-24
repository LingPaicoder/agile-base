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
import org.apache.commons.lang3.StringUtils
import org.dom4j.Element
import org.dom4j.io.SAXReader

class XMLBeanDefinitionParser : BeanDefinitionParser {

    override fun parse(source: Resource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull

        val containerElement = source.getInputStream().use { SAXReader().read(it).rootElement }
        val definitions = mutableListOf<BeanDefinition>()

        parseAutoScans(containerElement, definitions)
        parseBeans(containerElement, definitions)
        return definitions
    }

    private fun parseAutoScans(containerElement: Element, definitions: MutableList<BeanDefinition>) {
        val packages = containerElement.element(autoScansKey).elements(scanKey)
                .map { (it as Element).attributeValue(packageKey) }.toList()
        scanBeanDefinition(packages).forEach { definitions.add(it) }
    }

    private fun parseBeans(containerElement: Element, definitions: MutableList<BeanDefinition>) {
        containerElement.element(beansKey).elements(beanKey).map { beanElement ->
            beanElement as Element
            val id = beanElement.attributeValue(idKey)
            val clazz = beanElement.attributeValue(classKey)
            val isSingletonStr = beanElement.attributeValue(isSingletonKey)
            val isSingleton = if (StringUtils.isEmpty(isSingletonStr)) true else isSingletonStr.toBoolean()
            val definition = BeanDefinition(id, clazz, isSingleton)
            parseConstructorArgs(beanElement, definition)
            parseProperties(beanElement, definition)
            return@map definition
        }.forEach { definitions.add(it) }
    }

    private fun parseConstructorArgs(element: Element, definition: BeanDefinition) {
        val iterator = element.elementIterator(constructorArgKey)
        while (iterator.hasNext()) {
            val argElement = iterator.next() as Element
            val indexStr = argElement.attributeValue(indexKey)
            if (indexStr.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'index' attribute")
            }
            if (!StringUtil.isDigit(indexStr)) {
                throw BeanDefinitionException("The 'index' attribute of Tag 'constructor-arg' must be digit")
            }
            val index = indexStr.toInt()
            val type = argElement.attributeValue(typeKey)
            if (type.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'type' attribute")
            }
            val constructorArgValue = beanConstructorArgValueOf(argElement, index)
            val constructorArg = BeanConstructorArg(index, type, constructorArgValue)
            definition.constructorArgs.add(constructorArg)
        }
        definition.constructorArgs.sortBy { it.index }
    }

    private fun beanConstructorArgValueOf(propElement: Element, index: Int) =
            beanValueOf(propElement, "<constructor-arg> element for index '$index'")

    private fun parseProperties(element: Element, definition: BeanDefinition) {
        val iterator = element.elementIterator(propertyKey)
        while (iterator.hasNext()) {
            val propElement = iterator.next() as Element
            val propertyName = propElement.attributeValue(nameKey)
            if (propertyName.isNullOrBlank()) {
                throw BeanDefinitionException("Tag 'property' must have a 'name' attribute")
            }
            val propertyValue = beanPropertyValueOf(propElement, propertyName)
            val property = BeanProperty(propertyName, propertyValue)
            definition.properties.add(property)
        }
    }

    private fun beanPropertyValueOf(propElement: Element, propertyName: String) =
            beanValueOf(propElement, "<property> element for property '$propertyName'")

    private fun beanValueOf(propElement: Element, elementDesc: String): BeanPropertyValue {
        val isRefAttr = propElement.attribute(refKey) != null
        val isValueAttr = propElement.attribute(valueKey) != null
        return when {
            isRefAttr -> {
                val refName = propElement.attributeValue(refKey)
                if (refName.isNullOrBlank()) {
                    throw BeanDefinitionException("$elementDesc contains empty 'ref' attribute")
                }
                RuntimeBeanReferenceValue(refName)
            }
            isValueAttr -> {
                TypedStringValue(propElement.attributeValue(valueKey))
            }
            else -> throw BeanDefinitionException("$elementDesc must specify a ref or value")
        }
    }

}