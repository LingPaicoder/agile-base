package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.eq
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.gt
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.gte
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.lt
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.lte
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.support.DoubleRuler.beNullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class DoubleRulerTest{
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val weight: Double? = 1.0


    @Test
    fun nullValTest() {
        var num: Double? = null
        num must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21000, desc=num必须为Null")
        num = 1.0
        num alias "num" must beNullVal
    }

    @Test
    fun notNullTest() {
        var num: Double? = 1.0
        num must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21006, desc=num不能为Null")
        num = null
        num alias "num" must beNotNull
    }

    @Test
    fun eqTest() {
        weight must eq(1.0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21001, desc=weight必须等于20")
        weight alias "weight" must eq(20.0)
    }

    @Test
    fun gtTest() {
        weight must gt(0.0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21002, desc=weight必须大于20")
        weight alias "weight" must gt(20.0)
    }

    @Test
    fun gteTest() {
        weight must gte(1.0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21003, desc=weight必须大于或等于20")
        weight alias "weight" must gte(20.0)
    }

    @Test
    fun ltTest() {
        weight must lt(2.0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21004, desc=weight必须小于1")
        weight alias "weight" must lt(1.0)
    }

    @Test
    fun lteTest() {
        weight must lte(1.0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-21005, desc=weight必须小于或等于0")
        weight alias "weight" must lte(0.0)
    }
}