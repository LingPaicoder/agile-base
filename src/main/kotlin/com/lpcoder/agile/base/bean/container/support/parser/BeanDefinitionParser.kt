package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.core.resource.Resource

interface BeanDefinitionParser {
    fun parse(source: Resource): List<BeanDefinition>
}

val beansAttr = "beans"

val idAttr = "id"

val classAttr = "class"

val isSingletonAttr = "isSingleton"

val propertyElement = "property"

val nameAttr = "name"

val refAttr = "ref"

val valueAttr = "value"

val constructorArgElement = "constructor-arg"

val indexAttr = "index"

val typeAttr = "type"