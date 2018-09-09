package com.lpcoder.agile.base.util


object ClassUtil {

    // todo: study and optimize
    fun getDefaultClassLoader(): ClassLoader {
        return Thread.currentThread().contextClassLoader
    }
}