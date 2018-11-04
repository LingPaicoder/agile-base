package com.lpcoder.agile.base.bean.container.support.aspect

import java.lang.reflect.Method

abstract class BaseAspect<T, R>(order: Int = 1, methodMatcher: (Method) -> Boolean)
    : AbstractAspect<T, R>(order, methodMatcher) {

    override fun proceed(context: AspectContext<T, R>): R {
        if (!methodMatcher(context.targetMethod)) {
            return context.proceed()
        }
        return try {
            before(context)
            val result = context.proceed()
            afterReturning(context, result)
            result
        } catch (e: Throwable) {
            afterThrowing(context, e)
        } finally {
            after(context)
        }
    }

    abstract fun before(context: AspectContext<T, R>)
    abstract fun afterReturning(context: AspectContext<T, R>, result: R)
    abstract fun afterThrowing(context: AspectContext<T, R>, e: Throwable): R
    abstract fun after(context: AspectContext<T, R>)
}