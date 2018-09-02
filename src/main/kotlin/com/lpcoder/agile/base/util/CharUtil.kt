package com.lpcoder.agile.base.util

/**
 * @author: liurenpeng
 * @date: Created in 18-7-24
 */
object CharUtil {

    fun isEq(target: Char?, norm: Char?) = norm!! == target!!
    fun isGt(target: Char?, norm: Char?) = target!! > norm!!
    fun isGte(target: Char?, norm: Char?) = target!! >= norm!!
    fun isLt(target: Char?, norm: Char?) = target!! < norm!!
    fun isLte(target: Char?, norm: Char?) = target!! <= norm!!

}