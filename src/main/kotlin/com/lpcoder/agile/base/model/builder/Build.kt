package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.open.OpenPair
import com.lpcoder.agile.base.util.CollectionUtil
import java.util.Collections.singleton
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createType

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class ModelBuilder

class BuildSinglePair<out T>(modelBuilder: ModelBuilder, value: T) : OpenPair<ModelBuilder, T>(modelBuilder, value)
val <T> BuildSinglePair<T>.modelBuilder get() = first
val <T> BuildSinglePair<T>.targetClazz get() = second

class BuildMultiPair<out T>(modelBuilder: ModelBuilder, value: T) : OpenPair<ModelBuilder, T>(modelBuilder, value)
val <T> BuildMultiPair<T>.modelBuilder get() = first
val <T> BuildMultiPair<T>.targetClazz get() = second

infix fun <T : Any> ModelBuilder.buildSingle(clazz: KClass<T>) = BuildSinglePair(this, clazz)

infix fun <T : Any> ModelBuilder.buildMulti(clazz: KClass<T>) = BuildMultiPair(this, clazz)

infix fun <T : Any, I> BuildSinglePair<KClass<T>>.by(index: I): T? {
    val coll = this.modelBuilder buildMulti this.targetClazz by singleton(index)
    return if (CollectionUtil.isEmpty(coll)) null else coll.toList()[0]
}

/**
 * TODO 校验
 * 1. targetClazz是否已通过accompanyBy注册到BuildContext
 * 2. accompanyClazz与targetClazz单参构造函数参数类型是否匹配
 * 3. accompanyClazz是否非待构建类型(例如View)，是否为可build类型(例如DbModel)
 */
infix fun <T : Any, I> BuildMultiPair<KClass<T>>.by(indies: Collection<I>) : Collection<T> {
    val targets = buildTargets(this, indies)
    targets.forEach { it.buildInModelBuilder = this.modelBuilder }
    injectRelation(targets)
    return targets
}

@Suppress("UNCHECKED_CAST")
private fun <I, T : Any> buildTargets(
    buildMultiPair: BuildMultiPair<KClass<T>>, indies: Collection<I>): Set<T> {
    val accompanyClazz = BuildContext.accompanyHolder[buildMultiPair.targetClazz]
    val accompanyBuilder = BuildContext.builderHolder[accompanyClazz] as (Collection<I>) -> Map<I, Any>
    val accompanies = accompanyBuilder.invoke(indies).values
    return accompanies.map { accompany ->
        buildMultiPair.targetClazz.constructors.stream()
            .filter { it.parameters.size == 1 }
            .filter { it.parameters[0].type == accompanyClazz!!.createType() }
            .findFirst().map { it.call(accompany) }.orElse(null)
    }.toSet()
}

private fun <T : Any> injectRelation(targets: Set<T>) {

}

var Any.buildInModelBuilder : ModelBuilder? by ModelBuilderDelegate()

class ModelBuilderDelegate {
    private val mutableMap: MutableMap<Any, MutableMap<String, ModelBuilder?>> = mutableMapOf()
    operator fun getValue(thisRef: Any, property: KProperty<*>): ModelBuilder? {
        return mutableMap[thisRef]?.get(property.toString())
    }
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: ModelBuilder?) {
        mutableMap.computeIfAbsent(thisRef) { mutableMapOf() }
        mutableMap[thisRef]!![property.toString()] = value
    }
}
