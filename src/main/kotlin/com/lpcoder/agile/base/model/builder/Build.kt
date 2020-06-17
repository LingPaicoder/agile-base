package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class ModelBuilder

class BuildSinglePair<out A>(modelBuilder: ModelBuilder, value: A)
    : OpenPair<ModelBuilder, A>(modelBuilder, value)

class BuildMultiPair<out A>(modelBuilder: ModelBuilder, value: A)
    : OpenPair<ModelBuilder, A>(modelBuilder, value)

infix fun <T : Any> ModelBuilder.buildSingle(clazz: KClass<T>)
        = BuildSinglePair(this, clazz)

infix fun <T : Any> ModelBuilder.buildMulti(clazz: KClass<T>)
        = BuildMultiPair(this, clazz)

infix fun <V: Any, I> BuildSinglePair<KClass<V>>.by(index: I) : V? {
    return "0" as V
}

infix fun <V: Any, I> BuildMultiPair<KClass<V>>.by(indies: Collection<I>) =
    "0" as Collection<V>

