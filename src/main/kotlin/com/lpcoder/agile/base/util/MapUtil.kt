package com.lpcoder.agile.base.util

import org.apache.commons.collections.MapUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object MapUtil {

    fun <K, V> getFromMapForcibly(map: Map<K, V>, key: K, mapDesc: String): V =
            map[key] ?: throw IllegalArgumentException("no key in $mapDesc is $key. $mapDesc keys: ${map.keys}")

    fun isNotEmpty(target: Map<*, *>?) = MapUtils.isNotEmpty(target)
    fun isSizeEq(target: Map<*, *>?, norm: Int) =
            if (null == target) false else target.size == norm

    fun isSizeGt(target: Map<*, *>?, norm: Int) =
            if (null == target) false else target.size > norm

    fun isSizeGte(target: Map<*, *>?, norm: Int) =
            if (null == target) false else target.size >= norm

    fun isSizeLt(target: Map<*, *>?, norm: Int) =
            if (null == target) false else target.size < norm

    fun isSizeLte(target: Map<*, *>?, norm: Int) =
            if (null == target) false else target.size <= norm

    fun isKeyNotContainsNull(target: Map<*, *>?) =
            ArrayUtil.isNotContainsNull(target!!.keys.toTypedArray())
}