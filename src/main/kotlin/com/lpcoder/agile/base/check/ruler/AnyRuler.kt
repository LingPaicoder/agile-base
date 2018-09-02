package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler

object AnyRuler {
    val beNullVal = nullVal()
    val beNotNull = notNull()

    fun nullVal(code: Long = ANY_NULL_FAIL.code, desc: String = ANY_NULL_FAIL.desc)
            = Ruler.ofNullVal<Any?>(code, desc)

    fun notNull(code: Long = ANY_NOT_NULL_FAIL.code, desc: String = ANY_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Any?>(code, desc)
}