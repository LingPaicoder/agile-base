package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.open.OpenSingle
import com.lpcoder.agile.base.util.CollectionUtil.isEmpty
import java.util.stream.Collectors
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

infix fun <T : Any> ModelBuilder.buildMulti(clazz: KClass<T>) =
    BuildMultiHolder(clazz)

infix fun <T : Any> ModelBuilder.buildSingle(clazz: KClass<T>) =
    BuildSingleHolder(clazz)

infix fun <V: Any, T : Any, I> BuildMultiHolder<KClass<V>>.by(source: BuildSource<T, I>) =
    realBuild(
        this.value,
        BuildSourceHolder.ofSingle(source)
    )

infix fun <V: Any, I> BuildMultiHolder<KClass<V>>.by(indies: Collection<I>) =
    "0" as Collection<V>

infix fun <V: Any, I> BuildSingleHolder<KClass<V>>.by(index: I) : V? {
    return "0" as V
}

infix fun <V: Any, T : Any, I> BuildSingleHolder<KClass<V>>.by(source: BuildSource<T, I>) : V? {
    val collection = realBuild(
        this.value,
        BuildSourceHolder.ofSingle(source)
    )
    return if (isEmpty(collection))  null  else collection.first()
}

fun <V: Any> realBuild(clazz: KClass<V>, holder: BuildSourceHolder) : Collection<V> {
    val clazzCanBeInjected = holder.sources.stream().map{ it.clazz.java }.collect(Collectors.toSet())
    val fieldsToBeInjected = clazz.java.declaredFields.filter { clazzCanBeInjected.contains(it.type) }.toList()

    // TODO
    return emptyList()
}




class BuildSource<T : Any, I>(val clazz: KClass<T>, val indies: Collection<I>,
                              var predictor: (T) -> Boolean = {true} )
class BuildSourceHolder {
    val sources = mutableListOf<BuildSource<*, *>>()

    companion object {
        fun ofAll(vararg sources: BuildSource<*, *>): BuildSourceHolder {
            val sourceHolder = BuildSourceHolder()
            sourceHolder.sources.addAll(sources.toList())
            return sourceHolder
        }
        fun ofSingle(source: BuildSource<*, *>): BuildSourceHolder {
            val sourceHolder = BuildSourceHolder()
            sourceHolder.sources.add(source)
            return sourceHolder
        }
    }
}

fun <T : Any, I> the(clazz: KClass<T>, indies: Collection<I>): BuildSource<T, I> =
    BuildSource(clazz, indies)

fun <T : Any, I> the(clazz: KClass<T>, index: I): BuildSource<T, I> =
    BuildSource(clazz, setOf(index))

infix fun <T : Any, I> BuildSource<T, I>.which(predictor: (T) -> Boolean): BuildSource<T, I> {
    this.predictor = predictor
    return this
}

infix fun <T1 : Any, I1, T2: Any, I2> BuildSource<T1, I1>.and(source: BuildSource<T2, I2>) =
    BuildSourceHolder.ofAll(this, source)

infix fun <T: Any, I> BuildSourceHolder.and(source: BuildSource<T, I>) : BuildSourceHolder {
    this.sources.add(source)
    return this
}