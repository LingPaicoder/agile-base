package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.FloatRuler.eq
import com.lpcoder.agile.base.check.ruler.FloatRuler.gt
import com.lpcoder.agile.base.check.ruler.FloatRuler.gte
import com.lpcoder.agile.base.check.ruler.FloatRuler.lt
import com.lpcoder.agile.base.check.ruler.FloatRuler.lte
import com.lpcoder.agile.base.check.ruler.FloatRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.FloatRuler.beNullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class FloatRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val weight: Float? = 1.0F


    @Test
    fun nullValTest() {
        var num: Float? = null
        num must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20000, desc=num必须为Null")
        num = 1.0F
        num alias "num" must beNullVal
    }

    @Test
    fun notNullTest() {
        var num: Float? = 1.0F
        num must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20006, desc=num不能为Null")
        num = null
        num alias "num" must beNotNull
    }

    @Test
    fun eqTest() {
        weight must eq(1.0F)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20001, desc=weight必须等于20")
        weight alias "weight" must eq(20.0F)
    }

    @Test
    fun gtTest() {
        weight must gt(0.0F)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20002, desc=weight必须大于20")
        weight alias "weight" must gt(20.0F)
    }

    @Test
    fun gteTest() {
        weight must gte(1.0F)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20003, desc=weight必须大于或等于20")
        weight alias "weight" must gte(20.0F)
    }

    @Test
    fun ltTest() {
        weight must lt(2.0F)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20004, desc=weight必须小于1")
        weight alias "weight" must lt(1.0F)
    }

    @Test
    fun lteTest() {
        weight must lte(1.0F)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-20005, desc=weight必须小于或等于0")
        weight alias "weight" must lte(0.0F)
    }
}