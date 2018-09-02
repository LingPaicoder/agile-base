package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.MapUtil.isKeyNotContainsNull
import com.lpcoder.agile.base.util.MapUtil.isNotEmpty
import com.lpcoder.agile.base.util.MapUtil.isSizeEq
import com.lpcoder.agile.base.util.MapUtil.isSizeGt
import com.lpcoder.agile.base.util.MapUtil.isSizeGte
import com.lpcoder.agile.base.util.MapUtil.isSizeLt
import com.lpcoder.agile.base.util.MapUtil.isSizeLte

object MapRuler {
    val nullVal = nullVal()
    val notNull = notNull()
    val notEmpty = notEmpty()
    val keyNotContainsNull = keyNotContainsNull()

    fun nullVal(code: Long = MAP_NULL_FAIL.code, desc: String = MAP_NULL_FAIL.desc)
            = Ruler.ofNull<Map<*, *>?>(code, desc)

    fun notNull(code: Long = MAP_NOT_NULL_FAIL.code, desc: String = MAP_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Map<*, *>?>(code, desc)

    fun notEmpty(code: Long = MAP_NOT_EMPTY_FAIL.code, desc: String = MAP_NOT_EMPTY_FAIL.desc)
            = Ruler.of(code, desc, ::isNotEmpty)

    fun sizeEq(norm: Int, code: Long = MAP_SIZE_EQ_FAIL.code, desc: String = MAP_SIZE_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeEq)

    fun sizeGt(norm: Int, code: Long = MAP_SIZE_GT_FAIL.code, desc: String = MAP_SIZE_GT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeGt)

    fun sizeGte(norm: Int, code: Long = MAP_SIZE_GTE_FAIL.code, desc: String = MAP_SIZE_GTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeGte)

    fun sizeLt(norm: Int, code: Long = MAP_SIZE_LT_FAIL.code, desc: String = MAP_SIZE_LT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeLt)

    fun sizeLte(norm: Int, code: Long = MAP_SIZE_LTE_FAIL.code, desc: String = MAP_SIZE_LTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeLte)

    fun keyNotContainsNull(code: Long = MAP_KEY_NOT_CONTAINS_NULL_FAIL.code, desc: String = MAP_KEY_NOT_CONTAINS_NULL_FAIL.desc)
            = Ruler.of(code, desc, ::isKeyNotContainsNull)

}