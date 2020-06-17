package com.lpcoder.agile.base.lab.delegated.properties

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

class Account(map: Map<String, Any?>) {
    val name: String by map
    val password: String by map
}

class MutableAccount(val map: MutableMap<String, Any?>) {
    var name: String by map
    var password: String by map
}

fun main() {
    val account = Account(mapOf(
        "name" to "admin",
        "password" to "admin"
    ))
    println("Account(name=${account.name}, password = ${account.password})")

    val mutableAccount = MutableAccount(mutableMapOf(
        "name" to "admin",
        "password" to "admin"
    ))
    mutableAccount.password = "root"
    println("MutableAccount(name=${mutableAccount.name}, password = ${mutableAccount.password})")
}

