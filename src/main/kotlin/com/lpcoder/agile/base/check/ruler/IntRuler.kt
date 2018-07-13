package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.NumberUtil.isEq

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
object IntRuler {
    fun eq(norm: Int, code: Long = INT_EQ_FAIL.code, desc: String = INT_EQ_FAIL.desc) = Ruler.of(norm, code, desc, ::isEq)
}