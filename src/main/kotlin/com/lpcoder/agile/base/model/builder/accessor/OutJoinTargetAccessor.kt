package com.lpcoder.agile.base.model.builder.accessor

import com.lpcoder.agile.base.access.CacheAccessor
import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.model.builder.ModelBuilder
import com.lpcoder.agile.base.model.builder.buildInModelBuilder
import com.lpcoder.agile.base.model.builder.buildMulti
import com.lpcoder.agile.base.model.builder.by
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class OutJoinTargetAccessor<A : Any, AI, OJT>(private val outJoinTargetPoint: String) : CacheAccessor<A, OJT>() {
    @Suppress("UNCHECKED_CAST")
    override fun realGet(sources: Collection<A>): Map<A, OJT> {
        val accompanies = sources.toSet()
        if (CollectionUtil.isEmpty(accompanies)) return emptyMap()
        val outJoinAccompanyPointToMapperMap = BuildContext.outJoinHolder[accompanies.elementAt(0)::class]
        if (MapUtil.isEmpty(outJoinAccompanyPointToMapperMap)) return emptyMap()
        val mapper = outJoinAccompanyPointToMapperMap!![outJoinTargetPoint] as (Collection<AI>) -> Map<AI, Any>

        val indexer = BuildContext.indexerHolder[accompanies.elementAt(0)::class] as (A) -> AI
        val accompanyToAccompanyIndexMap : Map<A, AI> = accompanies.map { it to indexer.invoke(it) }.toMap()
        val accompanyIndexToOutJoinAccompanyMap = mapper.invoke(accompanyToAccompanyIndexMap.values)

        val outJoinAccompanyClazz = accompanyIndexToOutJoinAccompanyMap.values.elementAt(0)::class
        val accompanyToTargetMap = BuildContext.accompanyHolder.map { (k, v) -> v to k }.toMap()
        val outJoinTargetClazz = accompanyToTargetMap[outJoinAccompanyClazz] as KClass<Any>

        val outJoinAccompanyIndexer = BuildContext.indexerHolder[outJoinAccompanyClazz] as (Any) -> Any
        val outJoinAccompanyIndices = accompanyIndexToOutJoinAccompanyMap.values
            .map { outJoinAccompanyIndexer.invoke(it) }
        val outJoinTargets = ModelBuilder() buildMulti outJoinTargetClazz by outJoinAccompanyIndices

        return accompanyToAccompanyIndexMap.mapValues { (_, accompanyIndex) ->
            val outJoinAccompany = accompanyIndexToOutJoinAccompanyMap[accompanyIndex]
            val target = outJoinTargets.first { outJoinTarget ->
                outJoinTarget.buildInModelBuilder!!
                    .targetToAccompanyMap[outJoinTarget] == outJoinAccompany
            }
            print("---$target")
            target
        } as Map<A, OJT>
    }
}