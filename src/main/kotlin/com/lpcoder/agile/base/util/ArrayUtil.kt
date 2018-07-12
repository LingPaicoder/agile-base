package com.lpcoder.agile.base.util

import org.apache.commons.lang3.ArrayUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object ArrayUtil {
    fun isNotEmpty(target: Array<Any?>?) = ArrayUtils.isNotEmpty(target)
    fun isLengthEq(target: Array<Any?>?, norm: Int) = target!!.size == norm
    fun isLengthGt(target: Array<Any?>?, norm: Int) = target!!.size > norm
    fun isLengthGte(target: Array<Any?>?, norm: Int) = target!!.size >= norm
    fun isLengthLt(target: Array<Any?>?, norm: Int) = target!!.size < norm
    fun isLengthLte(target: Array<Any?>?, norm: Int) = target!!.size <= norm
    fun isContainsNull(target: Array<Any?>?) = ArrayUtils.contains(target, null)
    fun isNotContainsNull(target: Array<Any?>?) = !isContainsNull(target)
    fun isContainsDup(target: Array<Any?>?) = target!!.toCollection(HashSet()).size != target.size
    fun isNotContainsDup(target: Array<Any?>?) = !isContainsDup(target)
}