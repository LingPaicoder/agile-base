package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import com.lpcoder.agile.base.open.OpenPair
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

class JoinPair<out T, out J>(first: T, second: J) : OpenPair<T, J>(first, second)

infix fun <T: Any, J: Any> KClass<T>.join(clazz: KClass<J>) = JoinPair(this, clazz)

infix fun <T: Any, J: Any, JI> JoinPair<KClass<T>, KClass<J>>.by(mapper: (T) -> JI) {
    val map = BuildContext.joinHolder.computeIfAbsent(this.first) {mutableMapOf()}
    map.putIfAbsent(this.second, mapper)
}
