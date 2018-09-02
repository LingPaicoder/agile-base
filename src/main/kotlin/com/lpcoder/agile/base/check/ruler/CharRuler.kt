package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.CharUtil.isEq
import com.lpcoder.agile.base.util.CharUtil.isGt
import com.lpcoder.agile.base.util.CharUtil.isGte
import com.lpcoder.agile.base.util.CharUtil.isLt
import com.lpcoder.agile.base.util.CharUtil.isLte

/**
 * @author: liurenpeng
 * @date: Created in 18-7-24
 */
object CharRuler {
    val beNotNull = notNull()
    val beNullVal = nullVal()

    fun nullVal(code: Long = CHAR_NULL_FAIL.code, desc: String = CHAR_NULL_FAIL.desc)
            = Ruler.ofNullVal<Char?>(code, desc)

    fun notNull(code: Long = CHAR_NOT_NULL_FAIL.code, desc: String = CHAR_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Char?>(code, desc)

    fun eq(norm: Char, code: Long = CHAR_EQ_FAIL.code, desc: String = CHAR_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isEq)

    fun gt(norm: Char, code: Long = CHAR_GT_FAIL.code, desc: String = CHAR_GT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isGt)

    fun gte(norm: Char, code: Long = CHAR_GTE_FAIL.code, desc: String = CHAR_GTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isGte)

    fun lt(norm: Char, code: Long = CHAR_LT_FAIL.code, desc: String = CHAR_LT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isLt)

    fun lte(norm: Char, code: Long = CHAR_LTE_FAIL.code, desc: String = CHAR_LTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isLte)
}