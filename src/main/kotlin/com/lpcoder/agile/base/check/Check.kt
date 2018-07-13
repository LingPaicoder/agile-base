package com.lpcoder.agile.base.check

/**
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */

fun <T> check(target: T, description: String, vararg rulers: Ruler<T>) {
    try {
        rulers.forEach { it.check(target) }
    } catch (e: CheckException) {
        throw CheckException(e.code, description + e.desc, e)
    }
}

infix fun <T> Pair<T, String>.must(rulers: Collection<Ruler<T>>) {
    check(this.first, this.second, Ruler.ofAll(rulers))
}

infix fun <T> Pair<T, String>.must(ruler: Ruler<T>) {
    check(this.first, this.second, ruler)
}

infix fun <T> T.alias(alias: String) = Pair(this, alias)

fun <T> be(vararg rulers: Ruler<T>) = Ruler.ofAll(rulers.toList())

