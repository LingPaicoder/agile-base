package com.lpcoder.agile.base.check.ruler.support

import com.lpcoder.agile.base.check.CheckResultCodeEnum.*
import com.lpcoder.agile.base.check.and
import com.lpcoder.agile.base.check.ruler.Ruler
import com.lpcoder.agile.base.util.StringUtil.isLengthGt
import com.lpcoder.agile.base.util.StringUtil.isLengthGte
import com.lpcoder.agile.base.util.StringUtil.isLengthLt
import com.lpcoder.agile.base.util.StringUtil.isLengthLte
import com.lpcoder.agile.base.util.StringUtil.isAllLetter
import com.lpcoder.agile.base.util.StringUtil.isDigit
import com.lpcoder.agile.base.util.StringUtil.isEmail
import com.lpcoder.agile.base.util.StringUtil.isEmpty
import com.lpcoder.agile.base.util.StringUtil.isEq
import com.lpcoder.agile.base.util.StringUtil.isLengthEq
import com.lpcoder.agile.base.util.StringUtil.isNotEmpty
import com.lpcoder.agile.base.util.StringUtil.isIdCard
import com.lpcoder.agile.base.util.StringUtil.isPhone
import com.lpcoder.agile.base.util.StringUtil.isStandardDate
import com.lpcoder.agile.base.util.StringUtil.isStandardDatetime
import com.lpcoder.agile.base.util.StringUtil.isUrl

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object StrRuler {
    val beNullVal = nullVal()
    val beNotNull = notNull()
    val beEmpty = empty()
    val beNotEmpty = notEmpty()

    val beIdCard = idCard()
    val beEmail = email()
    val bePhone = phone()
    val beStandardDate = standardDate()
    val beStandardDatetime = standardDatetime()
    val beUrl = url()
    val beAllLetter = allLetter()
    val beDigit = digit()

    fun nullVal(code: Long = STR_NULL_FAIL.code, desc: String = STR_NULL_FAIL.desc)
            = Ruler.ofNullVal<String?>(code, desc)

    fun notNull(code: Long = STR_NOT_NULL_FAIL.code, desc: String = STR_NOT_NULL_FAIL.desc)
            = Ruler.ofNotNull<String?>(code, desc)

    fun empty(code: Long = STR_EMPTY_FAIL.code, desc: String = STR_EMPTY_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isEmpty)

    fun notEmpty(code: Long = STR_NOT_EMPTY_FAIL.code, desc: String = STR_NOT_EMPTY_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isNotEmpty)

    fun lengthEq(norm: Int, code: Long = STR_LENGTH_EQ_FAIL.code, desc: String = STR_LENGTH_EQ_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLengthEq)

    fun lengthGt(norm: Int, code: Long = STR_LENGTH_GT_FAIL.code, desc: String = STR_LENGTH_GT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLengthGt)

    fun lengthGte(norm: Int, code: Long = STR_LENGTH_GTE_FAIL.code, desc: String = STR_LENGTH_GTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLengthGte)

    fun lengthLt(norm: Int, code: Long = STR_LENGTH_LT_FAIL.code, desc: String = STR_LENGTH_LT_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLengthLt)

    fun lengthLte(norm: Int, code: Long = STR_LENGTH_LTE_FAIL.code, desc: String = STR_LENGTH_LTE_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isLengthLte)

    fun eq(norm: String, code: Long = STR_EQ_FAIL.code, desc: String = STR_EQ_FAIL.desc)
            = beNotNull and Ruler.of(norm, code, desc, ::isEq)

    fun idCard(code: Long = STR_ID_CARD_FAIL.code, desc: String = STR_ID_CARD_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isIdCard)

    fun email(code: Long = STR_EMAIL_FAIL.code, desc: String = STR_EMAIL_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isEmail)

    fun phone(code: Long = STR_PHONE_FAIL.code, desc: String = STR_PHONE_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isPhone)

    fun standardDate(code: Long = STR_STANDARD_DATE_FAIL.code, desc: String = STR_STANDARD_DATE_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isStandardDate)

    fun standardDatetime(code: Long = STR_STANDARD_DATETIME_FAIL.code, desc: String = STR_STANDARD_DATETIME_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isStandardDatetime)

    fun url(code: Long = STR_URL_FAIL.code, desc: String = STR_URL_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isUrl)

    fun allLetter(code: Long = STR_ALL_LETTER_FAIL.code, desc: String = STR_ALL_LETTER_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isAllLetter)

    fun digit(code: Long = STR_DIGIT_FAIL.code, desc: String = STR_DIGIT_FAIL.desc)
            = beNotNull and Ruler.of(code, desc, ::isDigit)
}
