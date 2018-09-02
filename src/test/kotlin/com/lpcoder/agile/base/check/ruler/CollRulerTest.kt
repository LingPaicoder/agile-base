package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.CollRuler.beNotContainsDup
import com.lpcoder.agile.base.check.ruler.CollRuler.beNotContainsNull
import com.lpcoder.agile.base.check.ruler.CollRuler.beNotEmpty
import com.lpcoder.agile.base.check.ruler.CollRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.CollRuler.beNullVal
import com.lpcoder.agile.base.check.ruler.CollRuler.sizeEq
import com.lpcoder.agile.base.check.ruler.CollRuler.sizeGt
import com.lpcoder.agile.base.check.ruler.CollRuler.sizeGte
import com.lpcoder.agile.base.check.ruler.CollRuler.sizeLt
import com.lpcoder.agile.base.check.ruler.CollRuler.sizeLte
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class CollRulerTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val signers: List<String> = listOf("a", "b")

    @Test
    fun nullValTest() {
        var list: Collection<*>? = null
        list must beNullVal

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14009, desc=list必须为Null")
        list = signers
        list alias "list" must beNullVal
    }

    @Test
    fun notNullTest() {
        var list: Collection<*>? = signers
        list must beNotNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14008, desc=list不能为Null")
        list = null
        list alias "list" must beNotNull
    }

    @Test
    fun notEmptyTest() {
        var list: Collection<*>? = signers
        list must beNotEmpty

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14000, desc=list不能为空")
        list = emptyList<String>()
        list alias "list" must beNotEmpty
    }

    @Test
    fun notContainsNullTest() {
        var list: Collection<*>? = signers
        list must beNotContainsNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14006, desc=list中不能有空值")
        list = listOf("a", null)
        list alias "list" must beNotContainsNull
    }

    @Test
    fun notContainsDupTest() {
        var list: Collection<*>? = signers
        list must beNotContainsDup

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14007, desc=list中不能有重复值")
        list = listOf("a", "a")
        list alias "list" must beNotContainsDup
    }

    @Test
    fun sizeEqTest() {
        signers must sizeEq(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14001, desc=signers的长度必须等于1")
        signers alias "signers" must sizeEq(1)
    }

    @Test
    fun sizeGtTest() {
        signers must sizeGt(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14002, desc=signers的长度必须大于2")
        signers alias "signers" must sizeGt(2)
    }

    @Test
    fun sizeGteTest() {
        signers must sizeGte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14003, desc=signers的长度必须大于或等于3")
        signers alias "signers" must sizeGte(3)
    }

    @Test
    fun sizeLtTest() {
        signers must sizeLt(3)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14004, desc=signers的长度必须小于2")
        signers alias "signers" must sizeLt(2)
    }

    @Test
    fun sizeLteTest() {
        signers must sizeLte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-14005, desc=signers的长度必须小于或等于1")
        signers alias "signers" must sizeLte(1)
    }
}