package com.lpcoder.agile.base.check

import com.lpcoder.agile.base.check.ruler.StrRuler
import com.lpcoder.agile.base.check.ruler.StrRuler.beEmpty
import com.lpcoder.agile.base.check.ruler.StrRuler.beIdCard
import com.lpcoder.agile.base.check.ruler.StrRuler.beNotNull
import com.lpcoder.agile.base.check.ruler.StrRuler.empty
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthGte
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthLte
import com.lpcoder.agile.base.util.CharUtil

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */
fun main(args: Array<String>) {
    println(CharUtil.isGte('2', '2'))
    println(CharUtil.isEq('2', '1'))
    val username = "lrp"
    doCheck(username, "姓名", beNotNull, lengthEq(4))
    Pair(username, "姓名") must be(beNotNull, lengthEq(4))
    username alias "姓名" must be(beNotNull, lengthEq(4))

    username alias "姓名" must beNotNull
    username alias "姓名" must beEmpty.or(lengthEq(4))
    val be_a_name = Ruler.ofAll(beNotNull, lengthEq(4))
    username alias "姓名" must be_a_name

    val idCardStr = "1"
    idCardStr alias "身份证号" must beIdCard
    val name = "1"
    empty().or(lengthGte(2), lengthLte(10))
    doCheck(idCardStr, "身份证号", StrRuler.beIdCard)
    val nameRuler: Ruler<String?> = Ruler.ofAll(lengthGte(2), lengthLte(10))
}