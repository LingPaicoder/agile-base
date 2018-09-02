package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.NumberUtil

object ByteRuler {
    val beNotNull = notNull()
    val beNullVal = nullVal()

    fun nullVal(code: Long = BYTE_NULL_FAIL.code, desc: String = BYTE_NULL_FAIL.desc)
            = Ruler.ofNullVal<Byte?>(code, desc)

    fun notNull(code: Long = BYTE_NOT_NULL_FAIL.code, desc: String = BYTE_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Byte?>(code, desc)

    fun eq(norm: Byte, code: Long = BYTE_EQ_FAIL.code, desc: String = BYTE_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, NumberUtil::isEq)

    fun gt(norm: Byte, code: Long = BYTE_GT_FAIL.code, desc: String = BYTE_GT_FAIL.desc)
            = Ruler.of(norm, code, desc, NumberUtil::isGt)

    fun gte(norm: Byte, code: Long = BYTE_GTE_FAIL.code, desc: String = BYTE_GTE_FAIL.desc)
            = Ruler.of(norm, code, desc, NumberUtil::isGte)

    fun lt(norm: Byte, code: Long = BYTE_LT_FAIL.code, desc: String = BYTE_LT_FAIL.desc)
            = Ruler.of(norm, code, desc, NumberUtil::isLt)

    fun lte(norm: Byte, code: Long = BYTE_LTE_FAIL.code, desc: String = BYTE_LTE_FAIL.desc)
            = Ruler.of(norm, code, desc, NumberUtil::isLte)
}