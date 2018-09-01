package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.Ruler
import com.lpcoder.agile.base.util.DateUtil.isAfter
import com.lpcoder.agile.base.util.DateUtil.isAfterOrEq
import com.lpcoder.agile.base.util.DateUtil.isBefore
import com.lpcoder.agile.base.util.DateUtil.isBeforeOrEq
import com.lpcoder.agile.base.util.DateUtil.isEq
import java.util.*

object DateRuler {
    val nullVal = nullVal()
    val notNull = notNull()

    fun nullVal(code: Long = DATE_NULL_FAIL.code, desc: String = DATE_NULL_FAIL.desc)
            = Ruler.ofNull<Date?>(code, desc)

    fun notNull(code: Long = DATE_NOT_NULL_FAIL.code, desc: String = DATE_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<Date?>(code, desc)

    fun eq(norm: Date, code: Long = DATE_EQ_FAIL.code, desc: String = DATE_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isEq)

    fun after(norm: Date, code: Long = DATE_AFTER_FAIL.code, desc: String = DATE_AFTER_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isAfter)

    fun afterOrEq(norm: Date, code: Long = DATE_AFTER_OR_EQ_FAIL.code, desc: String = DATE_AFTER_OR_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isAfterOrEq)

    fun before(norm: Date, code: Long = DATE_BEFORE_FAIL.code, desc: String = DATE_BEFORE_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isBefore)

    fun beforeOrEq(norm: Date, code: Long = DATE_BEFORE_OR_EQ_FAIL.code, desc: String = DATE_BEFORE_OR_EQ_FAIL.desc)
            = Ruler.of(norm, code, desc, ::isBeforeOrEq)

}