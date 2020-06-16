package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.open.OpenSingle
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

object ModelBuilder

infix fun <T : Any> ModelBuilder.buildSingle(clazz: KClass<T>) = BuildSingleHolder(clazz)

infix fun <T : Any> ModelBuilder.buildMulti(clazz: KClass<T>) = BuildMultiHolder(clazz)

class BuildSingleHolder<out A>(value: A) : OpenSingle<A>(value)

class BuildMultiHolder<out A>(value: A) : OpenSingle<A>(value)

infix fun <V: Any, I> BuildSingleHolder<KClass<V>>.by(index: I) : V? {
    return "0" as V
}

infix fun <V: Any, I> BuildMultiHolder<KClass<V>>.by(indies: Collection<I>) =
    "0" as Collection<V>

