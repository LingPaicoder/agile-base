package com.lpcoder.agile.base.bean.container.support.parser

import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import com.lpcoder.agile.base.bean.container.support.annotation.Bean
import com.lpcoder.agile.base.bean.container.support.definition.BeanConstructorArg
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition
import com.lpcoder.agile.base.bean.container.support.definition.BeanProperty
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValue
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.*
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
    val constructorToBeInjected = clazz.constructors.firstOrNull {
        it.isAnnotationPresent(AutoInject::class.java)
    } ?: return emptyList()
    val rst = constructorToBeInjected.parameterTypes.mapIndexed { index, paramClazz ->
        val value = if (ClassUtil.isBasicType(paramClazz)) {
            BeanPropertyValue(ClassUtil.getBasicTypeDefaultValue(paramClazz).toString(), BASIC_TYPE)
        } else {
            BeanPropertyValue(Introspector.decapitalize(paramClazz.simpleName), RUNTIME_BEAN_REFERENCE_TYPE)
        }
        BeanConstructorArg(index, paramClazz.name, value)
    }.toList()
    println("constructor rst:$rst")
    return rst
}

private fun parsePropertyInjectionInfo(clazz: Class<*>): List<BeanProperty> {
    val fieldsToBeInjected = clazz.fields.filter { it.isAnnotationPresent(AutoInject::class.java) }.toList()
    if (fieldsToBeInjected.isEmpty()) {
        return emptyList()
    }
    val rst = fieldsToBeInjected.map {
        val value = if (ClassUtil.isBasicType(it.type)) {
            BeanPropertyValue(ClassUtil.getBasicTypeDefaultValue(it.type).toString(), BASIC_TYPE)
        } else {
            BeanPropertyValue(Introspector.decapitalize(it.type.simpleName), RUNTIME_BEAN_REFERENCE_TYPE)
        }
        BeanProperty(it.name, value)
    }.toList()
    println("property rst:$rst")
    return rst
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