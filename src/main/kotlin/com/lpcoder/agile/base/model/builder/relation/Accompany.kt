package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

infix fun <T: Any, A: Any> KClass<T>.accompanyBy(accompany: KClass<A>) {
    BuildContext.accompanyHolder[this] = accompany
}