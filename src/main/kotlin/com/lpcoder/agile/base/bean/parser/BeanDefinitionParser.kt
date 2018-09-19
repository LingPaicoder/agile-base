package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.core.resource.Resource

interface BeanDefinitionParser {
    fun parse(source: Resource): List<BeanDefinition>
}

val idAttr = "id"

val classAttr = "class"

val isSingletonAttr = "isSingleton"
