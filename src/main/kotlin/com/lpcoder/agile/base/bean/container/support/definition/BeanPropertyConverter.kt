package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.support.RuntimeBeanReferenceValue
import com.lpcoder.agile.base.bean.container.support.definition.support.TypedStringValue

class BeanPropertyConverter(private val container: BeanContainer) {

    fun convert(value: BeanPropertyValue): Any = when (value) {
        is RuntimeBeanReferenceValue -> this.container.getBean(value.value)
        is TypedStringValue -> value.value
        else -> IllegalArgumentException("unsupported value type: ${value::class}")
    }

}