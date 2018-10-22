package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.core.resource.Resource

interface BeanDefinitionParser {
    fun parse(source: Resource): List<BeanDefinition>
}

val containerKey = "container"

val beansKey = "beans"

val beanKey = "bean"

val idKey = "id"

val classKey = "class"

val isSingletonKey = "isSingleton"

val propertyKey = "property"

val nameKey = "name"

val refKey = "ref"

val valueKey = "value"

val constructorArgKey = "constructor-arg"

val indexKey = "index"

val typeKey = "type"