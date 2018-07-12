package com.lpcoder.agile.base.util

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object MapUtil {
    fun isSizeEq(target: Map<Any?, Any?>?, norm: Int) = target!!.size == norm
    fun isSizeGt(target: Map<Any?, Any?>?, norm: Int) = target!!.size > norm
    fun isSizeGte(target: Map<Any?, Any?>?, norm: Int) = target!!.size >= norm
    fun isSizeLt(target: Map<Any?, Any?>?, norm: Int) = target!!.size < norm
    fun isSizeLte(target: Map<Any?, Any?>?, norm: Int) = target!!.size <= norm
    fun isKeyNotContainsNull(target: Map<Any?, Any?>?) = ArrayUtil.isNotContainsNull(target!!.keys.toTypedArray())
}