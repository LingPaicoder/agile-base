package com.lpcoder.agile.base.model.builder.delegate

import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */

class Join<T>(private val joinFieldName: String) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val joinClazz = property.returnType.jvmErasure
        val joinAccessor = thisRef.buildInModelBuilder!!.joinAccessorMap[joinClazz]
        val accompanies = thisRef.buildInModelBuilder!!.accompanyMap.values
        val joinIndex = accompany.javaClass.kotlin.memberProperties.stream()
            .filter { joinFieldName == it.name }
            .findFirst().map { it.get(accompany) }.orElse(null)
        return (joinAccessor!!.get(accompanies)[accompany] ?: error(""))[joinIndex] as T
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        throw UnsupportedOperationException("model builder delegate field not support set")
    }
}