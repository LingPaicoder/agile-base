package com.lpcoder.agile.base.util

import java.util.*

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object DateUtil {
    fun isEq(target: Date?, norm: Date?) = target!!.time == norm!!.time
    fun isAfter(target: Date?, norm: Date?) = target!!.time > norm!!.time
    fun isAfterOrEq(target: Date?, norm: Date?) = target!!.time >= norm!!.time
    fun isBefore(target: Date?, norm: Date?) = target!!.time < norm!!.time
    fun isBeforeOrEq(target: Date?, norm: Date?) = target!!.time <= norm!!.time
}