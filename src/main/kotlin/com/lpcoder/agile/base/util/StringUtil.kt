package com.lpcoder.agile.base.util

import org.apache.commons.lang3.StringUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StringUtil {
    fun isNotEmpty(target: String?) = !isEmpty(target)
    fun isEmpty(target: String?) = StringUtils.isEmpty(target)
    fun isLengthEq(target: String?, norm: Int) = target?.length == norm
    fun isEq(target: String?, norm: String) = norm == target
    fun isIdCard(target: String?) = IdCardUtil.validate(target!!)
    fun isPhone(target: String?) = Regex("[1][3456789]\\d{9}").matches(target!!)
    fun isNum(target: String?) = Regex("\\d+").matches(target!!)
    fun isStandardDate(target: String?) = Regex("\\d{4}-\\d{1,2}-\\d{1,2}").matches(target!!)
    fun isStandardDatetime(target: String?) = Regex("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}").matches(target!!)
    fun isEmail(target: String?) = Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$").matches(target!!)
    fun isAllLetter(target: String?) = Regex("^[A-Za-z]+$").matches(target!!)
}

