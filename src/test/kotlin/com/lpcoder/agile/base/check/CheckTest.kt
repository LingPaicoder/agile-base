package com.lpcoder.agile.base.check

import com.lpcoder.agile.base.check.ruler.StrRuler
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq
import com.lpcoder.agile.base.util.StringUtil
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class CheckTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Test
    fun test() {

        val idCardStr: String? = null
        //println(StringUtil.isEq(idCardStr, ""))
        //println(StringUtil.isEmpty(idCardStr))
        //doCheck(idCardStr, lengthEq(1))
        //idCardStr must be(lengthEq(1))
    }

}