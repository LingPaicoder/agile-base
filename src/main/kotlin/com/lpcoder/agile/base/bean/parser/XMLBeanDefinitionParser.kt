package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.bean.definition.BeanProperty
import com.lpcoder.agile.base.bean.definition.BeanPropertyValue
import com.lpcoder.agile.base.bean.definition.support.RuntimeBeanReferenceValue
import com.lpcoder.agile.base.bean.definition.support.TypedStringValue
import com.lpcoder.agile.base.bean.exception.BeanDefinitionException
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
            parseProperties(element, definition)
            return@map definition
        }.collect(Collectors.toList())
    }

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

    private fun beanPropertyValueOf(propElement: Element, propertyName: String): BeanPropertyValue {
        val isRefAttr = propElement.attribute(refAttr) != null
        val isValueAttr = propElement.attribute(valueAttr) != null
        val elementDesc = "<property> element for property '$propertyName'"
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