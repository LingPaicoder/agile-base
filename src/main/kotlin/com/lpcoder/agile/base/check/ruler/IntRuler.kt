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
object IntRuler {
    fun notNull(code: Long = INT_NOT_NULL_FAIL.code, desc: String = INT_NOT_NULL_FAIL.desc) = Ruler.ofNotNull<Int?>(code, desc)
    fun eq(norm: Int, code: Long = INT_EQ_FAIL.code, desc: String = INT_EQ_FAIL.desc) = Ruler.of(norm, code, desc, ::isEq)
    fun gt(norm: Int, code: Long = INT_GT_FAIL.code, desc: String = INT_GT_FAIL.desc) = Ruler.of(norm, code, desc, ::isGt)
    fun gte(norm: Int, code: Long = INT_GTE_FAIL.code, desc: String = INT_GTE_FAIL.desc) = Ruler.of(norm, code, desc, ::isGte)
    fun lt(norm: Int, code: Long = INT_LT_FAIL.code, desc: String = INT_LT_FAIL.desc) = Ruler.of(norm, code, desc, ::isLt)
    fun lte(norm: Int, code: Long = INT_LTE_FAIL.code, desc: String = INT_LTE_FAIL.desc) = Ruler.of(norm, code, desc, ::isLte)
}