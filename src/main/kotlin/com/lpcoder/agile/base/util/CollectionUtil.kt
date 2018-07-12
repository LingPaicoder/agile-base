package com.lpcoder.agile.base.util

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object CollectionUtil {
    fun isSizeEq(target: Collection<Any?>?, norm: Int) = target!!.size == norm
    fun isSizethGt(target: Collection<Any?>?, norm: Int) = target!!.size > norm
    fun isSizethGte(target: Collection<Any?>?, norm: Int) = target!!.size >= norm
    fun isSizethLt(target: Collection<Any?>?, norm: Int) = target!!.size < norm
    fun isSizethLte(target: Collection<Any?>?, norm: Int) = target!!.size <= norm
    fun isContainsNull(target: Collection<Any?>?) = ArrayUtil.isContainsNull(target!!.toTypedArray())
    fun isNotContainsNull(target: Collection<Any?>?) = !isContainsNull(target)
    fun isContainsDup(target: Collection<Any?>?) = ArrayUtil.isContainsDup(target!!.toTypedArray())
    fun isNotContainsDup(target: Collection<Any?>?) = !isContainsDup(target)
}