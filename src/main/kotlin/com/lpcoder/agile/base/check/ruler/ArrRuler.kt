package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.ArrayUtil.isLengthEq
import com.lpcoder.agile.base.util.ArrayUtil.isLengthGt
import com.lpcoder.agile.base.util.ArrayUtil.isLengthGte
import com.lpcoder.agile.base.util.ArrayUtil.isLengthLt
import com.lpcoder.agile.base.util.ArrayUtil.isLengthLte
import com.lpcoder.agile.base.util.ArrayUtil.isNotContainsDup
import com.lpcoder.agile.base.util.ArrayUtil.isNotContainsNull
import org.apache.commons.lang3.ArrayUtils.isNotEmpty

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object ArrRuler {
    fun notEmpty() = notEmpty(ARR_NOT_EMPTY_FAIL.code, ARR_NOT_EMPTY_FAIL.desc)
    fun notEmpty(code: Long, desc: String) = Ruler.of<Array<Any?>?>(code, desc, ::isNotEmpty)
    fun lengthEq(norm: Int) = lengthEq(norm, ARR_LENGTH_EQ_FAIL.code, ARR_LENGTH_EQ_FAIL.desc)
    fun lengthEq(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthEq)
    fun lengthGt(norm: Int) = lengthGt(norm, ARR_LENGTH_GT_FAIL.code, ARR_LENGTH_GT_FAIL.desc)
    fun lengthGt(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthGt)
    fun lengthGte(norm: Int) = lengthGte(norm, ARR_LENGTH_GTE_FAIL.code, ARR_LENGTH_GTE_FAIL.desc)
    fun lengthGte(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthGte)
    fun lengthLt(norm: Int) = lengthLt(norm, ARR_LENGTH_LT_FAIL.code, ARR_LENGTH_LT_FAIL.desc)
    fun lengthLt(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthLt)
    fun lengthLte(norm: Int) = lengthLte(norm, ARR_LENGTH_LTE_FAIL.code, ARR_LENGTH_LTE_FAIL.desc)
    fun lengthLte(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthLte)
    fun notContainsNull() = notContainsNull(ARR_NOT_CONTAINS_NULL_FAIL.code, ARR_NOT_CONTAINS_NULL_FAIL.desc)
    fun notContainsNull(code: Long, desc: String) = Ruler.of(code, desc, ::isNotContainsNull)
    fun notContainsDup() = notContainsDup(ARR_NOT_CONTAINS_DUP_FAIL.code, ARR_NOT_CONTAINS_DUP_FAIL.desc)
    fun notContainsDup(code: Long, desc: String) = Ruler.of(code, desc, ::isNotContainsDup)
}


