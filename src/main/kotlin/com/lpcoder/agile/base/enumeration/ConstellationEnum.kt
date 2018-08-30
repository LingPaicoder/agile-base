package com.lpcoder.agile.base.enumeration

import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.IntRuler.gt
import com.lpcoder.agile.base.check.ruler.IntRuler.gte
import com.lpcoder.agile.base.check.ruler.IntRuler.lte
import org.apache.commons.lang3.StringUtils

enum class ConstellationEnum(val minDate: String,
                             val maxDate: String, val desc: String) {
    Aquarius("0120", "0218", "水瓶座"),
    Pisces("0219", "0320", "双鱼座"),
    Aries("0321", "0419", "白羊座"),
    Taurus("0420", "0520", "金牛座"),
    Gemini("0521", "0621", "双子座"),
    Cancer("0622", "0722", "巨蟹座"),
    Leo("0723", "0822", "狮子座"),
    Virgo("0823", "0922", "处女座"),
    Libra("0923", "1023", "天秤座"),
    Scorpio("1024", "1122", "天蝎座"),
    Sagittarius("1123", "1221", "射手座"),
    Capricorn("1222", "0119", "魔羯座");

    companion object {
        fun getByMonthAndDay(month: Int, day: Int): ConstellationEnum {
            month must be(gte(1), lte(12))
            day must be(gt(1), lte(31))
            val date = StringUtils.join(month, day).toInt()
            return enumValues<ConstellationEnum>().first { constellation ->
                date > constellation.minDate.toInt() &&
                        date < constellation.maxDate.toInt()
            }.also { Capricorn }
        }
    }
}