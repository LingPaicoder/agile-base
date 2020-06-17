package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class SingleMapPair<out A, out B>(first: A, second: B) : OpenPair<A, B>(first, second)

infix fun <T: Any, F: Any> KClass<T>.singleMap(clazz: KClass<F>) =
    SingleMapPair(this, clazz)

infix fun <T: Any, F: Any, TI> SingleMapPair<KClass<T>, KClass<F>>.by(
    mapper: (Collection<TI>) -> Map<TI, F>) {
    val map = BuildContext.singleMapHolder.computeIfAbsent(this.first) {mutableMapOf()}
    map.putIfAbsent(this.second, mapper)
}