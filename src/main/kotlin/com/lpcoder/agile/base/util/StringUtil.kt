package com.lpcoder.agile.base.util

import org.apache.commons.lang3.StringUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StringUtil {

    fun getString(target: Any?): String = (target ?: "").toString()

    fun isNotEmpty(target: String?) = !isEmpty(target)
    fun isEmpty(target: String?) = target.isNullOrBlank()
    fun isEq(target: String?, norm: String?) = norm == target

    fun isLengthEq(target: String?, norm: Int) =
            if (null == target) false else target.length == norm

    fun isLengthGt(target: String?, norm: Int) =
            if (null == target) false else target.length > norm

    fun isLengthGte(target: String?, norm: Int) =
            if (null == target) false else target.length >= norm

    fun isLengthLt(target: String?, norm: Int) =
            if (null == target) false else target.length < norm

    fun isLengthLte(target: String?, norm: Int) =
            if (null == target) false else target.length <= norm

    fun isIdCard(target: String?) =
            if (null == target) false else IdCardUtil.validate(target)

    fun isPhone(target: String?) =
            if (null == target) false else Regex("[1]\\d{10}").matches(target)

    fun isDigit(target: String?) =
            if (null == target) false else Regex("\\d+").matches(target)

    fun isStandardDate(target: String?) =
            if (null == target) false else Regex("\\d{4}-\\d{1,2}-\\d{1,2}").matches(target)

    fun isStandardDatetime(target: String?) =
            if (null == target) false else Regex("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}").matches(target)

    fun isEmail(target: String?) =
            if (null == target) false else Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$").matches(target)

    fun isAllLetter(target: String?) =
            if (null == target) false else Regex("^[A-Za-z]+$").matches(target)

    fun isUrl(target: String?) =
            if (null == target) false else target.startsWith("http://") || target.startsWith("https://")
}

