package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.ArrRuler.beNotContainsDup
import com.lpcoder.agile.base.check.ruler.ArrRuler.beNotContainsNull
import com.lpcoder.agile.base.check.ruler.ArrRuler.beNotEmpty
import com.lpcoder.agile.base.check.ruler.ArrRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.ArrRuler.beNullVal
import com.lpcoder.agile.base.check.ruler.ArrRuler.lengthEq
import com.lpcoder.agile.base.check.ruler.ArrRuler.lengthGt
import com.lpcoder.agile.base.check.ruler.ArrRuler.lengthGte
import com.lpcoder.agile.base.check.ruler.ArrRuler.lengthLt
import com.lpcoder.agile.base.check.ruler.ArrRuler.lengthLte
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ArrRulerTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val signers: Array<String> = arrayOf("a", "b")

    @Test
    fun nullValTest() {
        var array: Array<*>? = null
        array must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13009, desc=array必须为Null")
        array = signers
        array alias "array" must beNullVal
    }

    @Test
    fun notNullTest() {
        var array: Array<*>? = signers
        array must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13008, desc=array不能为Null")
        array = null
        array alias "array" must beNotNull
    }

    @Test
    fun notEmptyTest() {
        var array: Array<*>? = signers
        array must beNotEmpty

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13000, desc=array不能为空")
        array = emptyArray<String>()
        array alias "array" must beNotEmpty
    }

    @Test
    fun notContainsNullTest() {
        var array: Array<*>? = signers
        array must beNotContainsNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13006, desc=array中不能有空值")
        array = arrayOf("a", null)
        array alias "array" must beNotContainsNull
    }

    @Test
    fun notContainsDupTest() {
        var array: Array<*>? = signers
        array must beNotContainsDup

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13007, desc=array中不能有重复值")
        array = arrayOf("a", "a")
        array alias "array" must beNotContainsDup
    }

    @Test
    fun lengthEqTest() {
        signers must lengthEq(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13001, desc=signers的长度必须等于1")
        signers alias "signers" must lengthEq(1)
    }

    @Test
    fun lengthGtTest() {
        signers must lengthGt(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13002, desc=signers的长度必须大于2")
        signers alias "signers" must lengthGt(2)
    }

    @Test
    fun lengthGteTest() {
        signers must lengthGte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13003, desc=signers的长度必须大于或等于3")
        signers alias "signers" must lengthGte(3)
    }

    @Test
    fun lengthLtTest() {
        signers must lengthLt(3)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13004, desc=signers的长度必须小于2")
        signers alias "signers" must lengthLt(2)
    }

    @Test
    fun lengthLteTest() {
        signers must lengthLte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-13005, desc=signers的长度必须小于或等于1")
        signers alias "signers" must lengthLte(1)
    }
}