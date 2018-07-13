package com.lpcoder.agile.base.check

import com.lpcoder.agile.base.check.*
import com.lpcoder.agile.base.check.ruler.StrRuler.empty
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq
import com.lpcoder.agile.base.check.ruler.StrRuler.notNull

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */
fun main(args: Array<String>) {
    val username = "lrp"
    check(username, "姓名", notNull(), lengthEq(4))
    Pair(username, "姓名") must be(notNull(), lengthEq(4))
    username alias "姓名" must be(notNull(), lengthEq(4))

    username alias "姓名" must notNull()
    username alias "姓名" must empty().or(lengthEq(4))
    val be_a_name = Ruler.ofAll(notNull(), lengthEq(4))
    username alias "姓名" must be_a_name
}