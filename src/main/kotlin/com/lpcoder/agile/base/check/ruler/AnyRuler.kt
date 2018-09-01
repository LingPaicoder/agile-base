package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum
import com.lpcoder.agile.base.check.Ruler

object AnyRuler {
    val notNull = notNull()
    fun notNull(code: Long = CheckResultCodeEnum.OBJ_NOT_NULL_FAIL.code,
                desc: String = CheckResultCodeEnum.OBJ_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Any?>(code, desc)
}