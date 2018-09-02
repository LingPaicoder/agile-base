package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.CollectionUtil.isNotContainsDup
import com.lpcoder.agile.base.util.CollectionUtil.isNotContainsNull
import com.lpcoder.agile.base.util.CollectionUtil.isNotEmpty
import com.lpcoder.agile.base.util.CollectionUtil.isSizeEq
import com.lpcoder.agile.base.util.CollectionUtil.isSizeGt
import com.lpcoder.agile.base.util.CollectionUtil.isSizeGte
import com.lpcoder.agile.base.util.CollectionUtil.isSizeLt
import com.lpcoder.agile.base.util.CollectionUtil.isSizeLte

object CollRuler {
    val beNullVal = nullVal()
    val beNotNull = notNull()
    val beNotEmpty = notEmpty()
    val beNotContainsNull = notContainsNull()
    val beNotContainsDup = notContainsDup()

    fun nullVal(code: Long = COLL_NULL_FAIL.code, desc: String = COLL_NULL_FAIL.desc)
            = Ruler.ofNullVal<Collection<*>?>(code, desc)

    fun notNull(code: Long = COLL_NOT_NULL_FAIL.code, desc: String = COLL_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Collection<*>?>(code, desc)

    fun notEmpty(code: Long = COLL_NOT_EMPTY_FAIL.code, desc: String = COLL_NOT_EMPTY_FAIL.desc)
            = Ruler.of(code, desc, ::isNotEmpty)

    fun sizeEq(norm: Int, code: Long = COLL_SIZE_EQ_FAIL.code, desc: String = COLL_SIZE_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeEq)

    fun sizeGt(norm: Int, code: Long = COLL_SIZE_GT_FAIL.code, desc: String = COLL_SIZE_GT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeGt)

    fun sizeGte(norm: Int, code: Long = COLL_SIZE_GTE_FAIL.code, desc: String = COLL_SIZE_GTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeGte)

    fun sizeLt(norm: Int, code: Long = COLL_SIZE_LT_FAIL.code, desc: String = COLL_SIZE_LT_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeLt)

    fun sizeLte(norm: Int, code: Long = COLL_SIZE_LTE_FAIL.code, desc: String = COLL_SIZE_LTE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isSizeLte)

    fun notContainsNull(code: Long = COLL_NOT_CONTAINS_NULL_FAIL.code, desc: String = COLL_NOT_CONTAINS_NULL_FAIL.desc)
            = Ruler.of(code, desc, ::isNotContainsNull)

    fun notContainsDup(code: Long = COLL_NOT_CONTAINS_DUP_FAIL.code, desc: String = COLL_NOT_CONTAINS_DUP_FAIL.desc)
            = Ruler.of(code, desc, ::isNotContainsDup)

}