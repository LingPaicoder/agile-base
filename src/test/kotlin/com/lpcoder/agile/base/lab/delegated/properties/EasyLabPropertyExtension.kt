package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.reflect.KProperty

/**
 * 实验后发现不可行，原因在于所有对象的同一个字段（例如email）,
 * 都被委托到了同一个LabPropertyAttachedDelegation。导致只能当静态属性而非实例属性用
 * @author liurenpeng
 * Created on 2020-06-17
 */

fun main() {
    val u1 = EasyUser("name1", 1, 1)
    u1.email= "u1@163.com"
    u1.height = 1

    val u2 = EasyUser("name2", 2, 2)
    u2.email= "u2@163.com"
    u2.height = 2

    println("u1:$u1")
    println("u2:$u2")
    println("u1 email:${u1.email} height:${u1.height}")
    println("u2 email:${u2.email} height:${u2.height}")
}

data class EasyUser(val name: String? = null, val age: Int = 0, val gender: Int = 0)

var EasyUser.email: String by EasyPropertyAttachedDelegation()
var EasyUser.height: Int by EasyPropertyAttachedDelegation()

class EasyPropertyAttachedDelegation<T> {
    private val mutableMap: MutableMap<String, Any?> = mutableMapOf()
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        println()
        return mutableMap[property.toString()] as T
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        mutableMap[property.toString()] = value
    }
}
