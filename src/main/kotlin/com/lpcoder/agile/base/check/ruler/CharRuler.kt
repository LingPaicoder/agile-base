package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.CHAR_EQ_FAIL
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.CharUtil.isEq

/**
 * @author: liurenpeng
 * @date: Created in 18-7-24
 */
object CharRuler {
    fun eq(norm: Char, code: Long = CHAR_EQ_FAIL.code, desc: String = CHAR_EQ_FAIL.desc) = Ruler.of(norm, code, desc, ::isEq)
}