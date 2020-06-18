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
 * JTAI:JoinTargetAccompanyIndex
 * @author liurenpeng
 * Created on 2020-06-18
 */
class JoinTargetAccessor<A: Any, JTAI, JT: Any>(private val joinTargetClazz: KClass<Any>) : CacheAccessor<A, Map<JTAI, JT>>() {
    @Suppress("UNCHECKED_CAST")
    override fun realGet(sources: Collection<A>): Map<A, Map<JTAI, JT>> {
        val accompanies = sources.toSet()
        if (CollectionUtil.isEmpty(accompanies)) return emptyMap()
        val joinAccompanyClazzToMapperMap = BuildContext.joinHolder[accompanies.elementAt(0)::class]
        if (MapUtil.isEmpty(joinAccompanyClazzToMapperMap)) return emptyMap()
        val joinAccompanyClazz = BuildContext.accompanyHolder[joinTargetClazz]
        val mapper = joinAccompanyClazzToMapperMap!![joinAccompanyClazz] as MutableList<(A) -> JTAI>

        val accompanyToJoinTargetAccompanyIndices : Map<A, Set<JTAI>> = accompanies.map { it to
                mapper.map { mapper -> (mapper.invoke(it)) }.toSet()}.toMap()
        return accompanyToJoinTargetAccompanyIndices.mapValues { (_, joinTargetAccompanyIndices) ->
            val targets = ModelBuilder() buildMulti joinTargetClazz by joinTargetAccompanyIndices
            targets.map { target -> parseTargetToAccompanyIndex(target) to target }.toMap()
        } as Map<A, Map<JTAI, JT>>
    }

    @Suppress("UNCHECKED_CAST")
    fun parseTargetToAccompanyIndex(target : Any) : Any {
        val modelBuilder = target.buildInModelBuilder
        val accompanyToIndexMap = modelBuilder!!.accompanyMap.map { (k, v) -> v to k}.toMap()
        return modelBuilder.targetToAccompanyMap.mapValues { accompanyToIndexMap[it.value] }[target] ?: error("")
    }
}