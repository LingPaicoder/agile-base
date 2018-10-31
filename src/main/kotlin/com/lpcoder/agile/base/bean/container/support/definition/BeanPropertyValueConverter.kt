package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.BASIC_TYPE
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.RUNTIME_BEAN_REFERENCE_TYPE

class BeanPropertyValueConverter(private val container: BeanContainer) {

    fun convert(value: BeanPropertyValue): Any = when (value.type) {
        RUNTIME_BEAN_REFERENCE_TYPE -> this.container.getBean(value.value)
        BASIC_TYPE -> value.value
    }

}