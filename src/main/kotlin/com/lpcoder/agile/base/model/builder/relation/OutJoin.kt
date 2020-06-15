package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class OutJoinHolder<out A>(first: A, second: String) : OpenPair<A, String>(first, second)

infix fun <T: Any> KClass<T>.outJoin(str: String) = OutJoinHolder(this, str)

infix fun <T: Any, F: Any, TI> OutJoinHolder<KClass<T>>.by(
    mapper: (Collection<TI>) -> Map<TI, F>) {
    return
}