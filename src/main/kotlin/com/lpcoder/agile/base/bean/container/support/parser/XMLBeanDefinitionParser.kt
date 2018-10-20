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
import java.util.stream.Collectors

class XMLBeanDefinitionParser : BeanDefinitionParser {

    override fun parse(source: Resource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull
        return source.getInputStream().use {
            SAXReader().read(it).rootElement
        }.elements().stream().map { element ->
            element as Element
            val id = element.attributeValue(idAttr)
            val clazz = element.attributeValue(classAttr)
            val isSingletonStr = element.attributeValue(isSingletonAttr)
            val isSingleton = if (StringUtils.isEmpty(isSingletonStr)) true else isSingletonStr.toBoolean()
            val definition = BeanDefinition(id, clazz, isSingleton)
            parseConstructorArgs(element, definition)
            parseProperties(element, definition)
            return@map definition
        }.collect(Collectors.toList())
    }

    private fun parseConstructorArgs(element: Element, definition: BeanDefinition) {
        val iterator = element.elementIterator(constructorArgElement)
        while (iterator.hasNext()) {
            val argElement = iterator.next() as Element
            val indexStr = argElement.attributeValue(indexAttr)
            if (StringUtil.isEmpty(indexStr)) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'index' attribute")
            }
            if (StringUtil.isDigit(indexStr)) {
                throw BeanDefinitionException("The 'index' attribute of Tag 'constructor-arg' must be digit")
            }
            val index = indexStr.toInt()
            val type = argElement.attributeValue(typeAttr)
            if (StringUtil.isEmpty(type)) {
                throw BeanDefinitionException("Tag 'constructor-arg' must have a 'type' attribute")
            }
            val constructorArgValue = beanConstructorArgValueOf(argElement, index)
            val constructorArg = BeanConstructorArg(index, type, constructorArgValue)
            definition.constructorArgs.add(constructorArg)
        }
    }

    private fun beanConstructorArgValueOf(propElement: Element, index: Int) =
            beanValueOf(propElement, "<constructor-arg> element for index '$index'")

    private fun parseProperties(element: Element, definition: BeanDefinition) {
        val iterator = element.elementIterator(propertyElement)
        while (iterator.hasNext()) {
            val propElement = iterator.next() as Element
            val propertyName = propElement.attributeValue(nameAttr)
            if (StringUtil.isEmpty(propertyName)) {
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
        val isRefAttr = propElement.attribute(refAttr) != null
        val isValueAttr = propElement.attribute(valueAttr) != null
        return when {
            isRefAttr -> {
                val refName = propElement.attributeValue(refAttr)
                if (StringUtil.isEmpty(refName)) {
                    throw BeanDefinitionException("$elementDesc contains empty 'ref' attribute")
                }
                RuntimeBeanReferenceValue(refName)
            }
            isValueAttr -> {
                TypedStringValue(propElement.attributeValue(valueAttr))
            }
            else -> throw BeanDefinitionException("$elementDesc must specify a ref or value")
        }
    }

}