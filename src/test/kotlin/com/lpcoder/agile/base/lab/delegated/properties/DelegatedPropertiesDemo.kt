package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

class NormalPropertiesDemo {
    var content: String = "NormalProperties init content"
}

class DelegatePropertiesDemo {
    var content: String by Content()
    override fun toString(): String {
        return "DelegatePropertiesDemo Class"
    }
}

class Content {
    private var realContent = "empty"
    operator fun getValue(demo: DelegatePropertiesDemo, property: KProperty<*>): String {
        println("$demo property '${property.name}' = '$realContent'")
        return realContent
    }
    operator fun setValue(demo: DelegatePropertiesDemo, property: KProperty<*>, value: String) {
        println("$demo property '${property.name}' is setting value: '$value'")
        realContent = value
    }
}

fun main() {
    val n = NormalPropertiesDemo()
    println(n.content)
    n.content = "Lao tze"
    println(n.content)
    println()
    println()

    val e = DelegatePropertiesDemo()
    println(e.content)
    e.content = "Confucius"
    println(e.content)
    println()
    println()

    val e2 = DelegatePropertiesDemo()
    println(e2.content)
    e2.content = "ConfuciusOfE2"
    println(e2.content)
    println()
    println()

    println(e.content)
}