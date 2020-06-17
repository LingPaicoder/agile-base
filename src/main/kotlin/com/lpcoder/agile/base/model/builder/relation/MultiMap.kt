package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-09
 */

class FlatMapPair<out T, out MM>(first: T, second: MM) : OpenPair<T, MM>(first, second)

infix fun <T: Any, MM: Any> KClass<T>.multiMap(clazz: KClass<MM>) =
    FlatMapPair(this, clazz)

infix fun <T: Any, MM: Any, TI> FlatMapPair<KClass<T>, KClass<MM>>.by(
    mapper: (Collection<TI>) -> Map<TI, Collection<MM>>) {
    val map = BuildContext.multiMapHolder.computeIfAbsent(this.first) {mutableMapOf()}
    map.putIfAbsent(this.second, mapper)
}