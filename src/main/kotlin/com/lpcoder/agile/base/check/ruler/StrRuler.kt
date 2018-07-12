package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.StringUtil.isEmpty
import com.lpcoder.agile.base.util.StringUtil.isLengthEq
import com.lpcoder.agile.base.util.StringUtil.isNotEmpty

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StrRuler {
    fun notNull() = notNull(STR_NOT_NULL_FAIL.code, STR_NOT_NULL_FAIL.desc)
    fun notNull(code: Long, desc: String) = Ruler.ofNotNull<String?>(code, desc)
    fun empty() = empty(STR_EMPTY_FAIL.code, STR_EMPTY_FAIL.desc)
    fun empty(code: Long, desc: String) = Ruler.of(code, desc, ::isEmpty)
    fun notEmpty() = notEmpty(STR_NOT_EMPTY_FAIL.code, STR_NOT_EMPTY_FAIL.desc)
    fun notEmpty(code: Long, desc: String) = Ruler.of(code, desc, ::isNotEmpty)
    fun lengthEq(norm: Int) = lengthEq(norm, STR_LENGTH_EQ_FAIL.code, STR_LENGTH_EQ_FAIL.desc)
    fun lengthEq(norm: Int, code: Long, desc: String) = Ruler.of(norm, code, desc, ::isLengthEq)
}
