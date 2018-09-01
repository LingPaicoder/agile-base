package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.LongRuler.eq
import com.lpcoder.agile.base.check.ruler.LongRuler.gt
import com.lpcoder.agile.base.check.ruler.LongRuler.gte
import com.lpcoder.agile.base.check.ruler.LongRuler.lt
import com.lpcoder.agile.base.check.ruler.LongRuler.lte
import com.lpcoder.agile.base.check.ruler.LongRuler.notNull
import com.lpcoder.agile.base.check.ruler.LongRuler.nullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class LongRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val weight: Long? = 1


    @Test
    fun nullValTest() {
        var num: Long? = null
        num must be(nullVal)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19000, desc=num必须为Null")
        num = 1
        num alias "num" must be(nullVal)
    }

    @Test
    fun notNullTest() {
        var num: Long? = 1
        num must notNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19006, desc=num不能为Null")
        num = null
        num alias "num" must notNull
    }

    @Test
    fun eqTest() {
        weight must eq(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19001, desc=weight必须等于19")
        weight alias "weight" must eq(19)
    }

    @Test
    fun gtTest() {
        weight must gt(0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19002, desc=weight必须大于19")
        weight alias "weight" must gt(19)
    }

    @Test
    fun gteTest() {
        weight must gte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19003, desc=weight必须大于或等于19")
        weight alias "weight" must gte(19)
    }

    @Test
    fun ltTest() {
        weight must lt(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19004, desc=weight必须小于1")
        weight alias "weight" must lt(1)
    }

    @Test
    fun lteTest() {
        weight must lte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-19005, desc=weight必须小于或等于0")
        weight alias "weight" must lte(0)
    }
}