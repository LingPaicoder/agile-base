package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.check
import com.lpcoder.agile.base.check.ruler.AnyRuler.notNull
import com.lpcoder.agile.base.check.ruler.AnyRuler.nullVal
import com.lpcoder.agile.base.check.must
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class AnyRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Test
    fun nullValTest() {
        var any: Any? = null
        any must be(nullVal)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10001, desc=any必须为Null")
        any = Any()
        check(any, "any", nullVal)
    }

    @Test
    fun notNullTest() {
        var any: Any? = Any()
        any must notNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-10000, desc=any不能为Null")
        any = null
        check(any, "any", notNull)
    }

}