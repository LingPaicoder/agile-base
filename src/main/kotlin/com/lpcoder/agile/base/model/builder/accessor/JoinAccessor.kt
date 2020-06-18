package com.lpcoder.agile.base.model.builder.accessor

import com.lpcoder.agile.base.access.CacheAccessor
import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.util.CollectionUtil
import com.lpcoder.agile.base.util.MapUtil
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class JoinAccessor<A: Any, JI, J>(private val joinClazz: KClass<*>) : CacheAccessor<A, Map<JI, J>>() {
    @Suppress("UNCHECKED_CAST")
    override fun realGet(sources: Collection<A>): Map<A, Map<JI, J>> {
        val accompanies = sources.toSet()
        if (CollectionUtil.isEmpty(accompanies)) return emptyMap()
        val joinClazzToMapperMap = BuildContext.joinHolder[accompanies.elementAt(0)::class]
        if (MapUtil.isEmpty(joinClazzToMapperMap)) return emptyMap()
        val mapper = joinClazzToMapperMap!![joinClazz] as MutableList<(A) -> JI>

        val targetToJoinIndices : Map<A, Set<JI>> = accompanies.map { it to
                mapper.map { mapper -> (mapper.invoke(it)) }.toSet()}.toMap()
        val builder = BuildContext.builderHolder[joinClazz] as (Collection<JI>) -> Map<JI, J>
        return targetToJoinIndices.mapValues { builder.invoke(it.value) }
    }
}