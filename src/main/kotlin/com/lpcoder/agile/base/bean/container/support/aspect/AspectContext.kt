package com.lpcoder.agile.base.bean.container.support.aspect

import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class AspectContext(val targetClass: Class<*>,
                          val targetObject: Any,
                          val targetMethod: Method,
                          private val methodProxy: MethodProxy,
                          val methodParams: Array<Any>,
                          aspectList: Set<AbstractAspect>) {
    private var aspectIndex: Int = 0
    private val sortedAspectList: List<AbstractAspect> = aspectList.sortedBy { it.order }

    fun proceed(): Any = if (aspectIndex < sortedAspectList.size) {
        sortedAspectList[aspectIndex++].proceed(this)
    } else {
        @Suppress("UNCHECKED_CAST")
        methodProxy.invokeSuper(targetObject, methodParams) as Any
    }

}