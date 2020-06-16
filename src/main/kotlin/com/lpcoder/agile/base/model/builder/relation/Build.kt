package com.lpcoder.agile.base.model.builder.relation

import com.lpcoder.agile.base.model.builder.BuildContext
import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

infix fun <T: Any, I> KClass<T>.buildBy(builder: (Collection<I>) -> Map<I, T>) {
    BuildContext.builderMap[this] = builder
}