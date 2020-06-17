package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class OutJoinPair<out A>(first: A, second: String) : OpenPair<A, String>(first, second)

infix fun <T: Any> KClass<T>.outJoin(str: String) = OutJoinPair(this, str)

infix fun <T: Any, F: Any, TI> OutJoinPair<KClass<T>>.by(
    mapper: (Collection<TI>) -> Map<TI, F>) {
    val map = BuildContext.outJoinHolder.computeIfAbsent(this.first) {mutableMapOf()}
    map.putIfAbsent(this.second, mapper)
}