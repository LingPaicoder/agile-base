package com.lpcoder.agile.base.enumeration

import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.IntRuler.gt
import com.lpcoder.agile.base.check.ruler.support.IntRuler.gte
import com.lpcoder.agile.base.check.ruler.support.IntRuler.lte
import org.apache.commons.lang3.StringUtils

enum class ConstellationEnum(val minDate: String,
                             val maxDate: String, val desc: String) {
    AQUARIUS("0120", "0218", "水瓶座"),
    PISCES("0219", "0320", "双鱼座"),
    ARIES("0321", "0419", "白羊座"),
    TAURUS("0420", "0520", "金牛座"),
    GEMINI("0521", "0621", "双子座"),
    CANCER("0622", "0722", "巨蟹座"),
    LEO("0723", "0822", "狮子座"),
    VIRGO("0823", "0922", "处女座"),
    LIBRA("0923", "1023", "天秤座"),
    SCORPIO("1024", "1122", "天蝎座"),
    SAGITTARIUS("1123", "1221", "射手座"),
    CAPRICORN("1222", "0119", "魔羯座");

    companion object {
        fun getByMonthAndDay(month: Int, day: Int): ConstellationEnum {
            month must be(gte(1), lte(12))
            day must be(gt(1), lte(31))
            val date = StringUtils.join(month, day).toInt()
            return enumValues<ConstellationEnum>().first { constellation ->
                date > constellation.minDate.toInt() &&
                        date < constellation.maxDate.toInt()
            }.also { CAPRICORN }
        }
    }
}