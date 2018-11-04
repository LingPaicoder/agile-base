package com.lpcoder.agile.base.bean.container.support.aspect

import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class AspectContext<T, R>(val targetClass: Class<T>,
                          val targetObject: T,
                          val targetMethod: Method,
                          val methodProxy: MethodProxy,
                          val methodParams: Array<Any>,
                          aspectList: List<AbstractAspect<T, R>>) {
    private var aspectIndex: Int = 0
    private val sortedAspectList: List<AbstractAspect<T, R>> = aspectList.sortedBy { it.order }

    fun proceed(): R = if (aspectIndex < sortedAspectList.size) {
        sortedAspectList[aspectIndex++].proceed(this)
    } else {
        @Suppress("UNCHECKED_CAST")
        methodProxy.invokeSuper(targetObject, methodParams) as R
    }

}