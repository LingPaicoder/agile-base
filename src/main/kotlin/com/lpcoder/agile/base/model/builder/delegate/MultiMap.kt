package com.lpcoder.agile.base.model.builder.delegate

import kotlin.reflect.KProperty

/**
 * @author liurenpeng
 * Created on 2020-06-18
 */
class MultiMap<T> {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return "" as T
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        throw UnsupportedOperationException("model builder delegate field not support set")
    }
}