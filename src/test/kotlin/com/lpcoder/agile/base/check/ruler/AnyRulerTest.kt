package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.doCheck
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNull
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.notNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class AnyRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Test
    fun nullValTest() {
        var any: Any? = null
        any must beNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10001, desc=any必须为Null")
        any = Any()
        doCheck(any, "any", beNull)
    }

    @Test
    fun notNullTest() {
        var any: Any? = Any()
        any must notNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10000, desc=any不能为Null")
        any = null
        doCheck(any, "any", notNull)
    }

}