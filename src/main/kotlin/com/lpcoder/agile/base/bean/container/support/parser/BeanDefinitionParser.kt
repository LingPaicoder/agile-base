package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import com.lpcoder.agile.base.bean.container.support.annotation.Bean
import com.lpcoder.agile.base.bean.container.support.definition.BeanConstructorArg
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.bean.container.support.definition.BeanProperty
import com.lpcoder.agile.base.bean.container.support.definition.support.RuntimeBeanReferenceValue
import com.lpcoder.agile.base.bean.container.support.definition.support.TypedStringValue
import com.lpcoder.agile.base.core.resource.Resource
import com.lpcoder.agile.base.util.ClassUtil
import java.beans.Introspector
import kotlin.streams.toList

interface BeanDefinitionParser {
    fun parse(source: Resource): List<BeanDefinition>
}

fun scanBeanDefinition(packages: List<String>): Set<BeanDefinition> {
    val classes = packages.map { ClassUtil.getClassSet(it) }.flatMap { it }.toSet()
    return classes.stream().filter { it.isAnnotationPresent(Bean::class.java) }.map {
        val id = if (it.getAnnotation(Bean::class.java).id.isBlank()) {
            Introspector.decapitalize(it.simpleName)
        } else {
            it.getAnnotation(Bean::class.java).id
        }
        val className = it.name
        val isSingleton = it.getAnnotation(Bean::class.java).isSingleton
        val definition = BeanDefinition(id, className, isSingleton)
        definition.constructorArgs.addAll(parseConstructorInjectionInfo(it))
        definition.properties.addAll(parsePropertyInjectionInfo(it))
        definition
    }.toList().toSet()
}

private fun parseConstructorInjectionInfo(clazz: Class<*>): List<BeanConstructorArg> {
    val constructorToInject = clazz.constructors.firstOrNull {
        it.isAnnotationPresent(AutoInject::class.java)
    } ?: return emptyList()
    return constructorToInject.parameterTypes.mapIndexed { index, paramClazz ->
        val value = if (ClassUtil.isBasicType(paramClazz)) {
            TypedStringValue(ClassUtil.getBasicTypeDefaultValue(paramClazz).toString())
        } else {
            RuntimeBeanReferenceValue(Introspector.decapitalize(paramClazz.simpleName))
        }
        BeanConstructorArg(index, paramClazz.name, value)
    }.toList()
}

private fun parsePropertyInjectionInfo(clazz: Class<*>): List<BeanProperty> {
    return emptyList()
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