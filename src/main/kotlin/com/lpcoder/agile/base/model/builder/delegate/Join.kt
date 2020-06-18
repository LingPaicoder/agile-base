package com.lpcoder.agile.base.model.builder.delegate

import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class Join<T>(private val joinFieldName: String) : ModelBuilderDelegate<T> {

    @Suppress("UNCHECKED_CAST")
    override fun buildTarget(thisRef: Any, property: KProperty<*>): T {
        val joinTargetClazz = property.returnType.jvmErasure
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val joinTargetAccessor = thisRef.buildInModelBuilder!!.joinTargetAccessorMap[joinTargetClazz]
        val accompanies = thisRef.buildInModelBuilder!!.accompanyMap.values
        val joinAccompanyIndex = accompany.javaClass.kotlin.memberProperties.stream()
            .filter { joinFieldName == it.name }
            .findFirst().map { it.get(accompany) }.orElse(null)
        return (joinTargetAccessor!!.get(accompanies)[accompany] ?: error(""))[joinAccompanyIndex] as T
    }

    @Suppress("UNCHECKED_CAST")
    override fun buildAccompany(thisRef: Any, property: KProperty<*>): T {
        val joinClazz = property.returnType.jvmErasure
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val joinAccessor = thisRef.buildInModelBuilder!!.joinAccessorMap[joinClazz]
        val accompanies = thisRef.buildInModelBuilder!!.accompanyMap.values
        val joinIndex = accompany.javaClass.kotlin.memberProperties.stream()
            .filter { joinFieldName == it.name }
            .findFirst().map { it.get(accompany) }.orElse(null)
        return (joinAccessor!!.get(accompanies)[accompany] ?: error(""))[joinIndex] as T
    }
}