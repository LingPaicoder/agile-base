package com.lpcoder.agile.base.check.ruler

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.alias
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.MapRuler.keyNotContainsNull
import com.lpcoder.agile.base.check.ruler.MapRuler.notEmpty
import com.lpcoder.agile.base.check.ruler.MapRuler.notNull
import com.lpcoder.agile.base.check.ruler.MapRuler.nullVal
import com.lpcoder.agile.base.check.ruler.MapRuler.sizeEq
import com.lpcoder.agile.base.check.ruler.MapRuler.sizeGt
import com.lpcoder.agile.base.check.ruler.MapRuler.sizeGte
import com.lpcoder.agile.base.check.ruler.MapRuler.sizeLt
import com.lpcoder.agile.base.check.ruler.MapRuler.sizeLte
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class MapRulerTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val map: Map<String, String> = mapOf("1" to "one", "2" to "two")

    @Test
    fun nullValTest() {
        var temp: Map<*, *>? = null
        temp must be(nullVal)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15008, desc=temp必须为Null")
        temp = map
        temp alias "temp" must be(nullVal)
    }

    @Test
    fun notNullTest() {
        var temp: Map<*, *>? = map
        temp must notNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15007, desc=temp不能为Null")
        temp = null
        temp alias "temp" must notNull
    }

    @Test
    fun notEmptyTest() {
        var temp: Map<*, *>? = map
        temp must notEmpty

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15000, desc=temp不能为空")
        temp = emptyMap<String, String>()
        temp alias "temp" must notEmpty
    }

    @Test
    fun keyNotContainsNullTest() {
        var temp: Map<*, *>? = map
        temp must keyNotContainsNull

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15006, desc=temp中不能有空值")
        temp = mapOf(null to "null", "1" to "one")
        temp alias "temp" must keyNotContainsNull
    }

    @Test
    fun sizeEqTest() {
        map must sizeEq(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15001, desc=map的长度必须等于1")
        map alias "map" must sizeEq(1)
    }

    @Test
    fun sizeGtTest() {
        map must sizeGt(1)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15002, desc=map的长度必须大于2")
        map alias "map" must sizeGt(2)
    }

    @Test
    fun sizeGteTest() {
        map must sizeGte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15003, desc=map的长度必须大于或等于3")
        map alias "map" must sizeGte(3)
    }

    @Test
    fun sizeLtTest() {
        map must sizeLt(3)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15004, desc=map的长度必须小于2")
        map alias "map" must sizeLt(2)
    }

    @Test
    fun sizeLteTest() {
        map must sizeLte(2)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-15005, desc=map的长度必须小于或等于1")
        map alias "map" must sizeLte(1)
    }
}