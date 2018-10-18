package com.lpcoder.agile.base.bean.container.support.definition

data class BeanProperty(val name: String, val value: BeanPropertyValue) {

    @Volatile private var converted = false
    @Volatile private var convertedValue: Any? = null

    @Synchronized
    fun setConvertedValue(convertedValue: Any?) {
        this.converted = true
        this.convertedValue = convertedValue
    }

    @Synchronized
    fun isConverted() = this.converted

    @Synchronized
    fun getConvertedValue() = this.convertedValue

}