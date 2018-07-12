package com.lpcoder.agile.base

import com.lpcoder.agile.base.check.check
import com.lpcoder.agile.base.check.ruler.StrRuler.empty
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */
fun main(args: Array<String>) {
    //val username = "lrp"
    //check(username, "姓名", notEmpty(), lengthEq(4))
    val username = "lrp"
    check(username, "姓名", empty().or(lengthEq(4)))
}