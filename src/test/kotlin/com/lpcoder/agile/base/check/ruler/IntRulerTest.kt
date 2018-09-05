package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.IntRuler.eq
import com.lpcoder.agile.base.check.ruler.IntRuler.gt
import com.lpcoder.agile.base.check.ruler.IntRuler.gte
import com.lpcoder.agile.base.check.ruler.IntRuler.lt
import com.lpcoder.agile.base.check.ruler.IntRuler.lte
import com.lpcoder.agile.base.check.ruler.IntRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.IntRuler.beNullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class IntRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val weight: Int? = 1


    @Test
    fun nullValTest() {
        var num: Int? = null
        num must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18000, desc=num必须为Null")
        num = 1
        num alias "num" must beNullVal
    }

    @Test
    fun notNullTest() {
        var num: Int? = 1
        num must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18006, desc=num不能为Null")
        num = null
        num alias "num" must beNotNull
    }

    @Test
    fun eqTest() {
        weight must eq(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18001, desc=weight必须等于18")
        weight alias "weight" must eq(18)
    }

    @Test
    fun gtTest() {
        weight must gt(0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18002, desc=weight必须大于18")
        weight alias "weight" must gt(18)
    }

    @Test
    fun gteTest() {
        weight must gte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18003, desc=weight必须大于或等于18")
        weight alias "weight" must gte(18)
    }

    @Test
    fun ltTest() {
        weight must lt(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18004, desc=weight必须小于1")
        weight alias "weight" must lt(1)
    }

    @Test
    fun lteTest() {
        weight must lte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18005, desc=weight必须小于或等于0")
        weight alias "weight" must lte(0)
    }
}