package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNotNull
import com.lpcoder.agile.base.core.resource.Resource
import org.apache.commons.lang3.StringUtils
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.util.stream.Collectors

class XMLBeanDefinitionParser : BeanDefinitionParser {

    override fun parse(source: Resource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull

        val root = SAXReader().read(source.getInputStream()).rootElement
        return root.elements().stream().map { element ->
            element as Element
            val id = element.attributeValue(idAttr)
            val clazz = element.attributeValue(classAttr)
            var isSingleton = true
            val isSingletonStr = element.attributeValue(isSingletonAttr)
            if (StringUtils.isNotEmpty(isSingletonStr)) {
                isSingleton = isSingletonStr.toBoolean()
            }
            return@map BeanDefinition(id, clazz, isSingleton)
        }.collect(Collectors.toList())
    }
}