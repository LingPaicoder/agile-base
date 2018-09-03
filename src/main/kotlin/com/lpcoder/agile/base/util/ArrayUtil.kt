package com.lpcoder.agile.base.util

import org.apache.commons.lang3.ArrayUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object ArrayUtil {
    fun isNotEmpty(target: Array<*>?) = ArrayUtils.isNotEmpty(target)
    fun isLengthEq(target: Array<*>?, norm: Int) =
            if (null == target) false else target.size == norm

    fun isLengthGt(target: Array<*>?, norm: Int) =
            if (null == target) false else target.size > norm

    fun isLengthGte(target: Array<*>?, norm: Int) =
            if (null == target) false else target.size >= norm

    fun isLengthLt(target: Array<*>?, norm: Int) =
            if (null == target) false else target.size < norm

    fun isLengthLte(target: Array<*>?, norm: Int) =
            if (null == target) false else target.size <= norm

    fun isContainsNull(target: Array<*>?) = ArrayUtils.contains(target, null)
    fun isNotContainsNull(target: Array<*>?) = !isContainsNull(target)
    fun isContainsDup(target: Array<*>?) = target!!.toCollection(HashSet()).size != target.size
    fun isNotContainsDup(target: Array<*>?) = !isContainsDup(target)
}