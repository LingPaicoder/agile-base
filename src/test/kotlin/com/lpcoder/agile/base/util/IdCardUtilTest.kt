package com.lpcoder.agile.base.util

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
        /*assertEquals(GenderEnum.WOMAN, IdCardUtil.getGenderByIdCard(cnIdCard))
        assertEquals("1966-08-27", IdCardUtil.getStandardBirthByIdCard(cnIdCard))
        assertEquals("19660827", IdCardUtil.getNoHyphenBirthByIdCard(cnIdCard))
        assertEquals(1966, IdCardUtil.getYearByIdCard(cnIdCard))
        assertEquals(8, IdCardUtil.getMonthByIdCard(cnIdCard))
        assertEquals(27, IdCardUtil.getDateByIdCard(cnIdCard))
        assertEquals("处女座", IdCardUtil.getConstellationById(cnIdCard))
        assertEquals("马", IdCardUtil.getZodiacById(cnIdCard))
        assertEquals("丙午", IdCardUtil.getChineseEraById(cnIdCard))
        assertEquals("吉林", IdCardUtil.getProvinceByIdCard(cnIdCard))*/
    }

    @Test
    fun test() {
        // todo: why is "[Ljava.lang.String;@ee7d9f1" but not "ab"
        println(join("a", "b"))
        println(StringUtils.join("a", "b"))
    }

    fun join(vararg targets: String): String = StringUtils.join(targets)


}