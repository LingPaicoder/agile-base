package com.lpcoder.agile.base.util

import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.DateRuler.notNull
import com.lpcoder.agile.base.check.ruler.IntRuler.gte
import com.lpcoder.agile.base.check.ruler.IntRuler.lte
import com.lpcoder.agile.base.constant.STANDARD_DATETIME_FORMAT
import com.lpcoder.agile.base.constant.STANDARD_DATE_FORMAT
import com.lpcoder.agile.base.enumeration.WeekEnum
import org.apache.commons.lang3.time.DateFormatUtils
import org.apache.commons.lang3.time.DateUtils
import java.util.*

/**
 * @author: liurenpeng
 * @date: Created in 18-7-12
 */
object DateUtil {
    fun getCurrentDateStr() = getDateStr(Date())
    fun getCurrentDateTimeStr() = getDateTimeStr(Date())

    fun getDateStrByLong(time: Long) = getDateStr(Date(time))
    fun getDateTimeStrByLong(time: Long) = getDateTimeStr(Date(time))

    fun getDateStr(date: Date) = formatDate(date, STANDARD_DATE_FORMAT)
    fun getDateTimeStr(date: Date) = formatDate(date, STANDARD_DATETIME_FORMAT)
    fun formatDate(date: Date, format: String) = DateFormatUtils.format(date, format)

    fun getDateByDateStr(dateStr: String) = parseDate(dateStr, STANDARD_DATE_FORMAT)
    fun getDateByDateTimeStr(dateStr: String) = parseDate(dateStr, STANDARD_DATETIME_FORMAT)
    fun parseDate(dateStr: String, format: String) = DateUtils.parseDate(dateStr, format)

    fun isEq(target: Date?, norm: Date?) = target!!.time == norm!!.time
    fun isAfter(target: Date?, norm: Date?) = target!!.time > norm!!.time
    fun isAfterOrEq(target: Date?, norm: Date?) = target!!.time >= norm!!.time
    fun isBefore(target: Date?, norm: Date?) = target!!.time < norm!!.time
    fun isBeforeOrEq(target: Date?, norm: Date?) = target!!.time <= norm!!.time

    /**
     * 获取当月天数
     */
    fun getDayNumByMonth(year: Int, month: Int): Int {
        month must be(gte(1), lte(12))
        return when (month) {
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> 31
        }
    }

    /**
     * 是否闰年
     */
    fun isLeapYear(year: Int) = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

    /**
     * 获取日期对应的星期
     */
    fun getWeek(date: Date): WeekEnum {
        date must notNull
        val calender = Calendar.getInstance()
        calender.time = date
        return WeekEnum.getByCode(calender.get(Calendar.DAY_OF_WEEK))
    }
}