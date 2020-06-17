package com.lpcoder.agile.base.lab.delegated.properties

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

val synchronizedLazyImpl = lazy {
    println("lazyValueSynchronized1 3!")
    println("lazyValueSynchronized1 2!")
    println("lazyValueSynchronized1 1!")
    "Hello, lazyValueSynchronized1 ! "
}

val lazyValueSynchronized1: String by synchronizedLazyImpl

val lazyValueSynchronized2: String by lazy {
    println("lazyValueSynchronized2 3!")
    println("lazyValueSynchronized2 2!")
    println("lazyValueSynchronized2 1!")
    "Hello, lazyValueSynchronized2 ! "
}


fun main() {
    println(lazyValueSynchronized1)
    println(lazyValueSynchronized1)

    println(lazyValueSynchronized2)
    println(lazyValueSynchronized2)
}
