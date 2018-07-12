package com.lpcoder.agile.base.util

import org.apache.commons.lang3.StringUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StringUtil{
    fun isNotEmpty(target: String?): Boolean = !isEmpty(target)
    fun isEmpty(target: String?): Boolean = StringUtils.isEmpty(target)
    fun isLengthEq(target: String?, norm: Int): Boolean = target?.length == norm
}

