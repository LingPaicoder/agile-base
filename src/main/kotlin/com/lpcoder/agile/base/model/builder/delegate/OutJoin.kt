package com.lpcoder.agile.base.model.builder.delegate

import com.lpcoder.agile.base.access.IAccessor
import com.lpcoder.agile.base.access.access
import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import java.util.Collections.singleton
import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoin<T>(private val outJoinPoint: String) : ModelBuilderDelegate<T> {

    @Suppress("UNCHECKED_CAST")
    override fun buildTarget(thisRef: Any, property: KProperty<*>): T {
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val outJoinTargetAccessor = thisRef.buildInModelBuilder!!.outJoinTargetAccessorMap[outJoinPoint]
        val accompanies = thisRef.buildInModelBuilder!!.indexToAccompanyMap.values
        return access(accompanies, singleton(outJoinTargetAccessor as IAccessor<Any, T>))[accompany] ?: error("")
    }

    @Suppress("UNCHECKED_CAST")
    override fun buildAccompany(thisRef: Any, property: KProperty<*>): T {
        val accompany = thisRef.buildInModelBuilder!!.targetToAccompanyMap[thisRef]!!
        val outJoinAccessor = thisRef.buildInModelBuilder!!.outJoinAccessorMap[outJoinPoint]
        val accompanies = thisRef.buildInModelBuilder!!.indexToAccompanyMap.values
        return access(accompanies, singleton(outJoinAccessor as IAccessor<Any, T>))[accompany] ?: error("")
    }
}