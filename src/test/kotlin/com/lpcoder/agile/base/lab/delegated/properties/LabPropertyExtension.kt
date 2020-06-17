package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.reflect.KProperty

/**
 * 实验后发现不可行，原因在于所有对象的同一个字段（例如email）,
 * 都被委托到了同一个LabPropertyAttachedDelegation。导致只能当静态属性而非实例属性用
 * 原文链接：https://blog.inico.me/2016/10/26/Kotlin-Magic-Java-Swift#.Xul3tmozZQI
 * @author liurenpeng
 * Created on 2020-06-17
 */

fun main() {
    val u1 = LabUser()
    u1.email= "u1@163.com"
    u1.height = 1

    val u2 = LabUser()
    u2.email= "u2@163.com"
    u2.height = 2

    println("u1:$u1")
    println("u2:$u2")
    println("u1 email:${u1.email} height:${u1.height}")
    println("u2 email:${u2.email} height:${u2.height}")
}

data class LabUser(val name: String? = null, val age: Int = 0, val gender: Int = 0)

var LabUser.email: String by LabPropertyAttachedDelegation()
var LabUser.height: Int by LabPropertyAttachedDelegation()

private val Any.variablesMap by lazy {
    val mutableMap: MutableMap<String, Any?> = mutableMapOf()
    mutableMap//在Lazy表达式里，会将最后一个表达式的值隐式地作为返回值，不用显式声明retrun
}

class LabPropertyAttachedDelegation<T> {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return thisRef?.variablesMap?.get(property.toString()) as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        thisRef?.variablesMap?.set(property.toString(), value)
    }
}
