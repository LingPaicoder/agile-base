package com.lpcoder.agile.base.model.builder.accessor

import com.lpcoder.agile.base.access.CacheAccessor
import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoinAccessor<T: Any, AI, OJ>(private val outJoinPoint: String) : CacheAccessor<T, OJ>() {
    @Suppress("UNCHECKED_CAST")
    override fun realGet(sources: Collection<T>): Map<T, OJ> {
        val accompanies = sources.toSet()
        if (CollectionUtil.isEmpty(accompanies)) return emptyMap()
        val outJoinPointToMapperMap = BuildContext.outJoinHolder[accompanies.elementAt(0)::class]
        if (MapUtil.isEmpty(outJoinPointToMapperMap)) return emptyMap()
        val mapper = outJoinPointToMapperMap!![outJoinPoint] as (Collection<AI>) -> Map<AI, OJ>

        //val accompanyToAccompanyIndexMap = accompanies.map { it to it.i }

        val modelBuilder = accompanies.elementAt(0).buildInModelBuilder
        val accompanyToAccompanyIndexMap = modelBuilder!!.accompanyMap.map { (k, v) -> v to k  }.toMap()
        val targetToAccompanyIndexMap : Map<T, AI> = modelBuilder.targetToAccompanyMap
            .map { (target, accompany) -> target as T to accompanyToAccompanyIndexMap[accompany] as AI }.toMap()
        val accompanyIndexToOutJoinMap = mapper.invoke(targetToAccompanyIndexMap.values)
        return targetToAccompanyIndexMap.mapValues { accompanyIndexToOutJoinMap[it.value] ?: error("") }
    }
}