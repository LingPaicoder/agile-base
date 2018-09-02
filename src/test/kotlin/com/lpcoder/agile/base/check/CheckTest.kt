package com.lpcoder.agile.base.check

import com.lpcoder.agile.base.check.*
import com.lpcoder.agile.base.check.ruler.StrRuler.empty
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq
import com.lpcoder.agile.base.check.ruler.StrRuler.notNull
import com.lpcoder.agile.base.util.CharUtil
import org.apache.commons.lang3.CharUtils

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */
fun main(args: Array<String>) {
    println(CharUtil.isGte('2','2'))
    println(CharUtil.isEq('2','1'))
    /*val username = "lrp"
    check(username, "姓名", beNotNull(), lengthEq(4))
    Pair(username, "姓名") must be(beNotNull(), lengthEq(4))
    username alias "姓名" must be(beNotNull(), lengthEq(4))

    username alias "姓名" must beNotNull()
    username alias "姓名" must empty().or(lengthEq(4))
    val be_a_name = Ruler.ofAll(beNotNull(), lengthEq(4))
    username alias "姓名" must be_a_name*/
}