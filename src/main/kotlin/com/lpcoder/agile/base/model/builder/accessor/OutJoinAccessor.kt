package com.lpcoder.agile.base.model.builder.accessor

import com.lpcoder.agile.base.access.CacheAccessor
import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoinAccessor<A : Any, AI, OJ>(private val outJoinPoint: String) : CacheAccessor<A, OJ>() {
    @Suppress("UNCHECKED_CAST")
    override fun realGet(sources: Collection<A>): Map<A, OJ> {
        val accompanies = sources.toSet()
        if (CollectionUtil.isEmpty(accompanies)) return emptyMap()
        val outJoinPointToMapperMap = BuildContext.outJoinHolder[accompanies.elementAt(0)::class]
        if (MapUtil.isEmpty(outJoinPointToMapperMap)) return emptyMap()
        val mapper = outJoinPointToMapperMap!![outJoinPoint] as (Collection<AI>) -> Map<AI, OJ>

        val indexer = BuildContext.indexerHolder[accompanies.elementAt(0)::class] as (A) -> AI
        val accompanyToAccompanyIndexMap : Map<A, AI> = accompanies.map { it to indexer.invoke(it) }.toMap()
        val accompanyIndexToOutJoinMap = mapper.invoke(accompanyToAccompanyIndexMap.values)
        return accompanyToAccompanyIndexMap.mapValues { accompanyIndexToOutJoinMap[it.value] ?: error("") }
    }
}