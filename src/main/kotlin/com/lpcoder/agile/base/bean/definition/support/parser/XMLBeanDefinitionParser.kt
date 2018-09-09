package com.lpcoder.agile.base.bean.definition.support.parser

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.bean.definition.support.resource.BeanDefinitionResource
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNotNull

class XMLBeanDefinitionParser : BeanDefinitionParser {
    override fun parse(source: BeanDefinitionResource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull
        return XmlMapper()
                .readValue<BeanDefinitionWrapper>(source.getInputStream(),
                        BeanDefinitionWrapper::class.java)?.beans ?: emptyList()
    }
}