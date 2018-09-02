package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.ByteRuler.eq
import com.lpcoder.agile.base.check.ruler.ByteRuler.gt
import com.lpcoder.agile.base.check.ruler.ByteRuler.gte
import com.lpcoder.agile.base.check.ruler.ByteRuler.lt
import com.lpcoder.agile.base.check.ruler.ByteRuler.lte
import com.lpcoder.agile.base.check.ruler.ByteRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.ByteRuler.beNullVal
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ByteRulerTest {
    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val age: Byte? = 1

    @Test
    fun nullValTest() {
        var num: Byte? = null
        num must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16000, desc=num必须为Null")
        num = 1
        num alias "num" must beNullVal
    }

    @Test
    fun notNullTest() {
        var num: Byte? = 1
        num must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16006, desc=num不能为Null")
        num = null
        num alias "num" must beNotNull
    }

    @Test
    fun eqTest() {
        age must eq(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16001, desc=age必须等于20")
        age alias "age" must eq(20)
    }

    @Test
    fun gtTest() {
        age must gt(0)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16002, desc=age必须大于20")
        age alias "age" must gt(20)
    }

    @Test
    fun gteTest() {
        age must gte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16003, desc=age必须大于或等于20")
        age alias "age" must gte(20)
    }

    @Test
    fun ltTest() {
        age must lt(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16004, desc=age必须小于1")
        age alias "age" must lt(1)
    }

    @Test
    fun lteTest() {
        age must lte(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-16005, desc=age必须小于或等于0")
        age alias "age" must lte(0)
    }

}