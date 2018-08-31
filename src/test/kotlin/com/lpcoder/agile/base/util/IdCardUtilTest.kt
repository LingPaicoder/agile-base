package com.lpcoder.agile.base.util

import com.lpcoder.agile.base.enumeration.ChineseZodiacEnum
import com.lpcoder.agile.base.enumeration.ConstellationEnum
import com.lpcoder.agile.base.enumeration.GenderEnum
import com.lpcoder.agile.base.enumeration.ProvinceEnum
import org.apache.commons.lang3.StringUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class IdCardUtilTest {

    private val cnIdCard = "222323196608271521"
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    @Test
    fun testValidate() {
        assertEquals(true, IdCardUtil.validate(cnIdCard))
        assertEquals(currentYear - 1966, IdCardUtil.getAge(cnIdCard))
        assertEquals("1966-08-27", IdCardUtil.getStandardBirthDay(cnIdCard))
        assertEquals("19660827", IdCardUtil.getNoHyphenBirthDay(cnIdCard))
        assertEquals(1966, IdCardUtil.getBirthYear(cnIdCard))
        assertEquals(8, IdCardUtil.getBirthMonth(cnIdCard))
        assertEquals(27, IdCardUtil.getBirthDate(cnIdCard))
        assertEquals(GenderEnum.WOMAN, IdCardUtil.getGender(cnIdCard))
        assertEquals(ProvinceEnum.JI_LIN, IdCardUtil.getProvince(cnIdCard))
        assertEquals(ConstellationEnum.VIRGO, IdCardUtil.getConstellation(cnIdCard))
        assertEquals(ChineseZodiacEnum.HORSE, IdCardUtil.getChineseZodiac(cnIdCard))
        assertEquals("丙午", IdCardUtil.getChineseEra(cnIdCard))
    }

    @Test
    fun test() {
        // todo: why is "[Ljava.lang.String;@ee7d9f1" but not "ab"
        println(join("a", "b"))
        println(StringUtils.join("a", "b"))
    }

    private fun join(vararg targets: String): String = StringUtils.join(targets)


}