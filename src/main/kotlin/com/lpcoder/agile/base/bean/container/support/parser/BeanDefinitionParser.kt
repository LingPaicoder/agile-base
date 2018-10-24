package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.ClassUtil

interface BeanDefinitionParser {
    fun parse(source: Resource): List<BeanDefinition>
}

fun scanBeanDefinition(packages: List<String>): Set<BeanDefinition> {
    val classes = packages.map { ClassUtil.getClassSet(it) }.flatMap { it }.toSet()
    return emptySet()
}

val containerKey = "container"

val autoScansKey = "auto-scans"

val scanKey = "scan"

val packageKey = "package"

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