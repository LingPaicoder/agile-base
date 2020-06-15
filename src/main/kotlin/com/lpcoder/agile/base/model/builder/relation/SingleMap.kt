package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class MapHolder<out A, out B>(first: A, second: B) : OpenPair<A, B>(first, second)

infix fun <T: Any, F: Any> KClass<T>.singleMap(clazz: KClass<F>) =
    MapHolder(this, clazz)

infix fun <T: Any, F: Any, TI> MapHolder<KClass<T>, KClass<F>>.by(
    mapper: (Collection<TI>) -> Map<TI, F>) {
    return
}