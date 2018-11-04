package com.lpcoder.agile.base.bean.container.support.aspect

import java.lang.reflect.Method

abstract class BaseAspect(order: Int = 1, methodMatcher: (Method) -> Boolean)
    : AbstractAspect(order, methodMatcher) {

    override fun proceed(context: AspectContext): Any {
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

    abstract fun before(context: AspectContext)
    abstract fun afterReturning(context: AspectContext, result: Any)
    abstract fun afterThrowing(context: AspectContext, e: Throwable): Any
    abstract fun after(context: AspectContext)
}