package com.lpcoder.agile.base

import com.lpcoder.agile.base.util.ClassUtil

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
fun main(args: Array<String>) {
    ClassUtil.getClassSet("com.lpcoder.agile.base").forEach {
        println(it)
    }
}