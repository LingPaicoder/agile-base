package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.NumberUtil.isEq
import com.lpcoder.agile.base.util.NumberUtil.isGt
import com.lpcoder.agile.base.util.NumberUtil.isGte
import com.lpcoder.agile.base.util.NumberUtil.isLt
import com.lpcoder.agile.base.util.NumberUtil.isLte

/**
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
object LongRuler {
    val notNull = notNull()
    val nullVal = nullVal()

    fun nullVal(code: Long = LONG_NULL_FAIL.code, desc: String = LONG_NULL_FAIL.desc)
            = Ruler.ofNullVal<Long?>(code, desc)

    fun notNull(code: Long = LONG_NOT_NULL_FAIL.code, desc: String = LONG_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Long?>(code, desc)

    fun eq(norm: Long, code: Long = LONG_EQ_FAIL.code, desc: String = LONG_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isEq)

    fun gt(norm: Long, code: Long = LONG_GT_FAIL.code, desc: String = LONG_GT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isGt)

    fun gte(norm: Long, code: Long = LONG_GTE_FAIL.code, desc: String = LONG_GTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isGte)

    fun lt(norm: Long, code: Long = LONG_LT_FAIL.code, desc: String = LONG_LT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isLt)

    fun lte(norm: Long, code: Long = LONG_LTE_FAIL.code, desc: String = LONG_LTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isLte)
}