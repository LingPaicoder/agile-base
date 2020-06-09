package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.open.OpenSingle
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

object BuildContext {
    val indexerMap = mutableMapOf<KClass<*>, Any>()
    val builderMap = mutableMapOf<KClass<*>, Any>()
}

infix fun <T: Any, I> KClass<T>.indexBy(indexer: (T) -> I) {
    BuildContext.indexerMap[this] = indexer
}

infix fun <T: Any, I> KClass<T>.buildBy(builder: (Collection<I>) -> Map<I, T>) {
    BuildContext.builderMap[this] = builder
}

object ModelBuilder

class BuildMultiHolder<out A>(value: A) : OpenSingle<A>(value)

class BuildSingleHolder<out A>(value: A) : OpenSingle<A>(value)

infix fun <T : Any> ModelBuilder.buildMulti(clazz: KClass<T>) = BuildMultiHolder(clazz)

infix fun <T : Any> ModelBuilder.buildSingle(clazz: KClass<T>) = BuildSingleHolder(clazz)

infix fun <V: Any, I> BuildMultiHolder<KClass<V>>.by(indies: Collection<I>) =
    "0" as Collection<V>

infix fun <V: Any, I> BuildSingleHolder<KClass<V>>.by(index: I) : V? {
    return "0" as V
}