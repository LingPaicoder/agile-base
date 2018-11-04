package com.lpcoder.agile.base.bean.container.support.aspect

import java.lang.reflect.Method

abstract class AbstractAspect<T, R>(val order: Int = 1,
                                    val methodMatcher: (Method) -> Boolean) {
    abstract fun proceed(context: AspectContext<T, R>): R
}