package com.lpcoder.agile.base.model.builder.delegate

import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoin<T>(private val outJoinPoint: String) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val outJoinAccessor = thisRef.buildInModelBuilder!!.outJoinAccessorMap[outJoinPoint]
        val accompanies = thisRef.buildInModelBuilder!!.accompanyMap.values
        return (outJoinAccessor!!.get(accompanies)[accompany] ?: error("")) as T
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        throw UnsupportedOperationException("model builder delegate field not support set")
    }
}