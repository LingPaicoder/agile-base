package com.lpcoder.agile.base.util

import kotlin.reflect.KClass


object ClassUtil {

    // todo: study and optimize
    fun getDefaultClassLoader(): ClassLoader {
        return Thread.currentThread().contextClassLoader
    }

    fun loadClass(type: String, classLoader: ClassLoader = getDefaultClassLoader()): Class<*> =
            when (type) {
                "byte" -> Byte::class.java
                "short" -> Short::class.java
                "int" -> Int::class.java
                "long" -> Long::class.java
                "float" -> Float::class.java
                "double" -> Float::class.java
                "char" -> Char::class.java
                "boolean" -> Boolean::class.java
                else -> classLoader.loadClass(type)
            }
}