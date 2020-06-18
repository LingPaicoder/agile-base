package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */

class Demo {
    var content: String by DemoContent("abc")
    var name: String = ""
    override fun toString(): String {
        return "DelegatePropertiesDemo Class"
    }
}

class DemoContent(val test: String) {
    private var realContent = "empty"
    operator fun getValue(demo: Demo, property: KProperty<*>): String {
        println("$demo property '${property.name}' = '$realContent'")
        return realContent
    }
    operator fun setValue(demo: Demo, property: KProperty<*>, value: String) {
        println("$demo property '${property.name}' is setting value: '$value'")
        realContent = value
    }
}