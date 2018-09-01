package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum
import com.lpcoder.agile.base.check.Ruler
import java.util.*

object DateRuler {

    val notNull = notNull()
    fun notNull(code: Long = CheckResultCodeEnum.DATE_NOT_NULL_FAIL.code,
                desc: String = CheckResultCodeEnum.DATE_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Date?>(code, desc)

}