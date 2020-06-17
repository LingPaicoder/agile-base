package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.reflect.KProperty

/**
 * 注意，有内存泄露风险
 * @author liurenpeng
 * Created on 2020-06-17
 */

fun main() {
    val u1 = User("name1", 1, 1)
    u1.email = "u1@163.com"
    u1.height = 1

    val u2 = User("name2", 2, 2)
    u2.email = "u2@163.com"
    u2.height = 2

    println("u1:$u1")
    println("u2:$u2")
    println("u1 email:${u1.email} height:${u1.height}")
    println("u2 email:${u2.email} height:${u2.height}")
}

data class User(val name: String? = null, val age: Int = 0, val gender: Int = 0)

var User.email: String? by PropertyAttachedDelegation()
var User.height: Int? by PropertyAttachedDelegation()

class PropertyAttachedDelegation<T> {
    private val mutableMap: MutableMap<Any, MutableMap<String, Any?>> = mutableMapOf()
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return mutableMap[thisRef]?.get(property.toString()) as T?
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        mutableMap.computeIfAbsent(thisRef) { mutableMapOf() }
        mutableMap[thisRef]!![property.toString()] = value
    }
}
