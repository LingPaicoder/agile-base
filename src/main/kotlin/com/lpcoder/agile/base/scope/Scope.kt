package com.lpcoder.agile.base.scope

import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNull
import java.util.concurrent.ConcurrentHashMap

/**
 * @author liurenpeng
 * Created on 2020-06-15
 */
class Scope {

    private val map = ConcurrentHashMap<ScopeKey<*>, Any>()

    operator fun <T> set(key: ScopeKey<T>, value: T?) =
        if (value != null) map.put(key, value) else map.remove(key)

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: ScopeKey<T>): T? {
        var value = map[key] as T?
        if (value == null && key.initializer != null) {
            value = key.initializer.get()
            value?.let {map.put(key, value)} // TODO computeIfAbsent?
        }
        return value?:key.defaultValue
    }

    companion object ScopeUtils {
        private val scopeThreadLocal = ThreadLocal<Scope>()

        fun currentScope() : Scope? = scopeThreadLocal.get()

        fun beginScope() {
            scopeThreadLocal.get() must beNull
            scopeThreadLocal.set(Scope())
        }

        fun endScope() = scopeThreadLocal.remove()
    }
}