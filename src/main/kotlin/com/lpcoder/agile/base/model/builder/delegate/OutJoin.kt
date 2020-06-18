package com.lpcoder.agile.base.model.builder.delegate

import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoin<T>(private val outJoinPoint: String) : ModelBuilderDelegate<T> {

    override fun buildTarget(thisRef: Any, property: KProperty<*>): T {

        return "" as T
    }

    @Suppress("UNCHECKED_CAST")
    override fun buildAccompany(thisRef: Any, property: KProperty<*>): T {
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val outJoinAccessor = thisRef.buildInModelBuilder!!.outJoinAccessorMap[outJoinPoint]
        val accompanies = thisRef.buildInModelBuilder!!.indexToAccompanyMap.values
        return (outJoinAccessor!!.get(accompanies)[accompany] ?: error("")) as T
    }
}