package com.lpcoder.agile.base.check.ruler.support

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.and
import com.lpcoder.agile.base.check.ruler.Ruler
import com.lpcoder.agile.base.util.NumberUtil.isEq
import com.lpcoder.agile.base.util.NumberUtil.isGt
import com.lpcoder.agile.base.util.NumberUtil.isGte
import com.lpcoder.agile.base.util.NumberUtil.isLt
import com.lpcoder.agile.base.util.NumberUtil.isLte

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
object IntRuler {
    val beNotNull = notNull()
    val beNullVal = nullVal()

    fun nullVal(code: Long = INT_NULL_FAIL.code, desc: String = INT_NULL_FAIL.desc)
            = Ruler.ofNullVal<Int?>(code, desc)

    fun notNull(code: Long = INT_NOT_NULL_FAIL.code, desc: String = INT_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Int?>(code, desc)

    fun eq(norm: Int, code: Long = INT_EQ_FAIL.code, desc: String = INT_EQ_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isEq)

    fun gt(norm: Int, code: Long = INT_GT_FAIL.code, desc: String = INT_GT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isGt)

    fun gte(norm: Int, code: Long = INT_GTE_FAIL.code, desc: String = INT_GTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isGte)

    fun lt(norm: Int, code: Long = INT_LT_FAIL.code, desc: String = INT_LT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLt)

    fun lte(norm: Int, code: Long = INT_LTE_FAIL.code, desc: String = INT_LTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLte)
}