package com.lpcoder.agile.base

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
fun main(args: Array<String>) {
    val list = mutableListOf(2, 13, 6, 1)
    println(list)
    val newList = list.sortedBy { it }
    println(newList)
}