package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.StringUtil.isEmpty
import com.lpcoder.agile.base.util.StringUtil.isLengthEq
import com.lpcoder.agile.base.util.StringUtil.isNotEmpty
import com.lpcoder.agile.base.util.StringUtil.isNum

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StrRuler {
    fun notNull(code: Long = STR_NOT_NULL_FAIL.code, desc: String = STR_NOT_NULL_FAIL.desc) = Ruler.ofNotNull<String?>(code, desc)
    fun empty(code: Long = STR_EMPTY_FAIL.code, desc: String = STR_EMPTY_FAIL.desc) = Ruler.of(code, desc, ::isEmpty)
    fun notEmpty(code: Long = STR_NOT_EMPTY_FAIL.code, desc: String = STR_NOT_EMPTY_FAIL.desc) = Ruler.of(code, desc, ::isNotEmpty)
    fun lengthEq(norm: Int, code: Long = STR_LENGTH_EQ_FAIL.code, desc: String = STR_LENGTH_EQ_FAIL.desc) = Ruler.of(norm, code, desc, ::isLengthEq)

    fun num(code: Long = STR_NUM_FAIL.code, desc: String = STR_NUM_FAIL.desc) = Ruler.of(code, desc, ::isNum)
}
