package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class JoinHolder<out A, out B>(first: A, second: B) : OpenPair<A, B>(first, second)

infix fun <T: Any, J: Any> KClass<T>.join(clazz: KClass<J>) =
    JoinHolder(this, clazz)

infix fun <T: Any, J: Any, I> JoinHolder<KClass<T>, KClass<J>>.by(mapper: (T) -> I) {
    return
}
