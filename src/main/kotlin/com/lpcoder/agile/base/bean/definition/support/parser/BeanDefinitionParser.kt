package com.lpcoder.agile.base.bean.definition.support.parser

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.bean.definition.support.resource.BeanDefinitionResource

interface BeanDefinitionParser {
    fun parse(source: BeanDefinitionResource): List<BeanDefinition>
}

@JacksonXmlRootElement(localName = "wrapper")
class BeanDefinitionWrapper {
    @JacksonXmlElementWrapper(localName = "beans")
    @JacksonXmlProperty(localName = "bean")
    var beans: List<BeanDefinition>? = null
}

