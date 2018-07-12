package com.lpcoder.agile.base.check

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */

fun <T> check(target: T, description: String, vararg rulers: Ruler<T>) {
    try {
        rulers.forEach { it.verifier(target) }
    } catch (e: CheckException) {
        throw CheckException(e.code, description + e.desc, e)
    }
}