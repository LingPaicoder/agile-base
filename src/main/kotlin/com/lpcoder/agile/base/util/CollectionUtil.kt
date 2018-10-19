package com.lpcoder.agile.base.util

import org.apache.commons.collections.CollectionUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object CollectionUtil {
    fun isSizeEq(target: Collection<*>?, norm: Int) =
            if (null == target) false else target.size == norm

    fun isSizeGt(target: Collection<*>?, norm: Int) =
            if (null == target) false else target.size > norm

    fun isSizeGte(target: Collection<*>?, norm: Int) =
            if (null == target) false else target.size >= norm

    fun isSizeLt(target: Collection<*>?, norm: Int) =
            if (null == target) false else target.size < norm

    fun isSizeLte(target: Collection<*>?, norm: Int) =
            if (null == target) false else target.size <= norm

    fun isContainsNull(target: Collection<*>?) = ArrayUtil.isContainsNull(target!!.toTypedArray())
    fun isNotContainsNull(target: Collection<*>?) = !isContainsNull(target)
    fun isContainsDup(target: Collection<*>?) = ArrayUtil.isContainsDup(target!!.toTypedArray())
    fun isNotContainsDup(target: Collection<*>?) = !isContainsDup(target)
    fun isNotEmpty(target: Collection<*>?) = !isEmpty(target)
    fun isEmpty(target: Collection<*>?) = CollectionUtils.isEmpty(target)
}