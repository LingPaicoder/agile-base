package com.lpcoder.agile.base

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
fun main(args: Array<String>) {
    val map = mutableMapOf<String, String>()
    map.put("1", "one")
    map.put("2", "two")
    val map2 = map.toMap()
    println(map === map2)
}