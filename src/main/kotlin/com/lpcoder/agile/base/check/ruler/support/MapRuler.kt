package com.lpcoder.agile.base.check.ruler.support

import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_KEY_NOT_CONTAINS_NULL_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_NOT_EMPTY_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_NOT_NULL_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_NULL_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_SIZE_EQ_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_SIZE_GTE_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_SIZE_GT_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_SIZE_LTE_FAIL
import com.lpcoder.agile.base.check.CheckResultCodeEnum.MAP_SIZE_LT_FAIL
import com.lpcoder.agile.base.check.and
import com.lpcoder.agile.base.check.ruler.Ruler
import com.lpcoder.agile.base.util.MapUtil.isKeyNotContainsNull
import com.lpcoder.agile.base.util.MapUtil.isNotEmpty
import com.lpcoder.agile.base.util.MapUtil.isSizeEq
import com.lpcoder.agile.base.util.MapUtil.isSizeGt
import com.lpcoder.agile.base.util.MapUtil.isSizeGte
import com.lpcoder.agile.base.util.MapUtil.isSizeLt
import com.lpcoder.agile.base.util.MapUtil.isSizeLte

object MapRuler {
    val beNull = beNull()
    val notNull = notNull()
    val notEmpty = notEmpty()
    val notContainsNullKey = notContainsNullKey()

    fun beNull(code: Long = MAP_NULL_FAIL.code, desc: String = MAP_NULL_FAIL.desc)
            = Ruler.ofBeNull<Map<*, *>?>(code, desc)

    fun notNull(code: Long = MAP_NOT_NULL_FAIL.code, desc: String = MAP_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Map<*, *>?>(code, desc)

    fun notEmpty(code: Long = MAP_NOT_EMPTY_FAIL.code, desc: String = MAP_NOT_EMPTY_FAIL.desc)
            = notNull and Ruler.of(code, desc, ::isNotEmpty)

    fun sizeEq(norm: Int, code: Long = MAP_SIZE_EQ_FAIL.code, desc: String = MAP_SIZE_EQ_FAIL.desc)
            = notNull and Ruler.of(norm, code, desc, ::isSizeEq)

    fun sizeGt(norm: Int, code: Long = MAP_SIZE_GT_FAIL.code, desc: String = MAP_SIZE_GT_FAIL.desc)
            = notNull and Ruler.of(norm, code, desc, ::isSizeGt)

    fun sizeGte(norm: Int, code: Long = MAP_SIZE_GTE_FAIL.code, desc: String = MAP_SIZE_GTE_FAIL.desc)
            = notNull and Ruler.of(norm, code, desc, ::isSizeGte)

    fun sizeLt(norm: Int, code: Long = MAP_SIZE_LT_FAIL.code, desc: String = MAP_SIZE_LT_FAIL.desc)
            = notNull and Ruler.of(norm, code, desc, ::isSizeLt)

    fun sizeLte(norm: Int, code: Long = MAP_SIZE_LTE_FAIL.code, desc: String = MAP_SIZE_LTE_FAIL.desc)
            = notNull and Ruler.of(norm, code, desc, ::isSizeLte)

    fun notContainsNullKey(code: Long = MAP_KEY_NOT_CONTAINS_NULL_FAIL.code, desc: String = MAP_KEY_NOT_CONTAINS_NULL_FAIL.desc)
            = notNull and Ruler.of(code, desc, ::isKeyNotContainsNull)

}