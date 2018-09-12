package com.lpcoder.agile.base.check.ruler.support

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.and
import com.lpcoder.agile.base.check.ruler.Ruler
import com.lpcoder.agile.base.util.NumberUtil

object FloatRuler {
    val beNotNull = notNull()
    val beNullVal = nullVal()

    fun nullVal(code: Long = FLOAT_NULL_FAIL.code, desc: String = FLOAT_NULL_FAIL.desc)
            = Ruler.ofNullVal<Float?>(code, desc)

    fun notNull(code: Long = FLOAT_NOT_NULL_FAIL.code, desc: String = FLOAT_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Float?>(code, desc)

    fun eq(norm: Float, code: Long = FLOAT_EQ_FAIL.code, desc: String = FLOAT_EQ_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, NumberUtil::isEq)

    fun gt(norm: Float, code: Long = FLOAT_GT_FAIL.code, desc: String = FLOAT_GT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, NumberUtil::isGt)

    fun gte(norm: Float, code: Long = FLOAT_GTE_FAIL.code, desc: String = FLOAT_GTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, NumberUtil::isGte)

    fun lt(norm: Float, code: Long = FLOAT_LT_FAIL.code, desc: String = FLOAT_LT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, NumberUtil::isLt)

    fun lte(norm: Float, code: Long = FLOAT_LTE_FAIL.code, desc: String = FLOAT_LTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, NumberUtil::isLte)
}