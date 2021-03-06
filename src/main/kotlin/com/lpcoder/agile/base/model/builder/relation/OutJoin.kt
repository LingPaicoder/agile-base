package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class OutJoinPair<out T>(first: T, second: String) : OpenPair<T, String>(first, second)
val <T> OutJoinPair<T>.targetClazz get() = first
val <T> OutJoinPair<T>.outJoinPoint get() = second

infix fun <T: Any> KClass<T>.outJoin(str: String) = OutJoinPair(this, str)

infix fun <T: Any, OJ: Any, TI> OutJoinPair<KClass<T>>.by(mapper: (Collection<TI>) -> Map<TI, OJ>) {
    val outJoinPointToMapperMap = BuildContext.outJoinHolder.computeIfAbsent(this.targetClazz) {mutableMapOf()}
    outJoinPointToMapperMap.putIfAbsent(this.outJoinPoint, mapper)
}