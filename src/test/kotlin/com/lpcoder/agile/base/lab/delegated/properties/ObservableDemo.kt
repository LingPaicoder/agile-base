package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

class PostHierarchy {
    var level: String by Delegates.observable("P0",
        { _: KProperty<*>, oldValue: String, newValue: String ->
            println("$oldValue -> $newValue")
        })
}

fun main() {
    val ph = PostHierarchy()
    ph.level = "P1"
    ph.level = "P2"
    ph.level = "P3"
    println(ph.level)
}