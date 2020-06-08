package com.lpcoder.agile.base.model.builder

import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class FlatHolder<out A, out B>(first: A, second: B) : OpenPair<A, B>(first, second)

infix fun <T: Any, F: Any> KClass<T>.flat(clazz: KClass<F>) =
    FlatHolder(this, clazz)

infix fun <T: Any, F: Any, TI> FlatHolder<KClass<T>, KClass<F>>.by(
    mapper: (Collection<TI>) -> Map<TI, Collection<F>>) {
    return
}