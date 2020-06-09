package com.lpcoder.agile.base.open

/**
 * @author liurenpeng
 * Created on 2020-06-04
 */

open class OpenSingle<out A>(val value: A) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OpenSingle<*>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "OpenSingle(value=$value)"
    }
}

open class OpenPair<out A, out B>(val first: A, val second: B) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OpenPair<*, *>

        if (first != other.first) return false
        if (second != other.second) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first?.hashCode() ?: 0
        result = 31 * result + (second?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "OpenPair(first=$first, second=$second)"
    }
}
