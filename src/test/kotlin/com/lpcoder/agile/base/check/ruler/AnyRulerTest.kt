package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.*
import com.lpcoder.agile.base.check.ruler.AnyRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.AnyRuler.beNullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class AnyRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Test
    fun nullValTest() {
        var any: Any? = null
        any must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10001, desc=any必须为Null")
        any = Any()
        doCheck(any, "any", beNullVal)
    }

    @Test
    fun notNullTest() {
        var any: Any? = Any()
        any must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10000, desc=any不能为Null")
        any = null
        doCheck(any, "any", beNotNull)
    }

}