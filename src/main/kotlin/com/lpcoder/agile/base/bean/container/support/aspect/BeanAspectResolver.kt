package com.lpcoder.agile.base.bean.container.support.aspect

import com.lpcoder.agile.base.bean.container.BeanContainer
import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor

class BeanAspectResolver(private val container: BeanContainer) {

    internal val beanIdToAspectSetMap = mutableMapOf<String, Set<AbstractAspect>>()

    init {
        container.getBeanDefinitions().forEach { beanDefinition ->
            val beanClass = container.getBeanClass(beanDefinition.id)
            val aspectSet = container.getAspects().filter { aspect ->
                beanClass.declaredMethods.any { method ->
                    aspect.methodMatcher(method)
                }
            }.toSet()
            beanIdToAspectSetMap[beanDefinition.id] = aspectSet
        }

    }

    fun createProxy(bean: Any, beanId: String): Any {
        val targetClass = container.getBeanClass(beanId)
        val methodInterceptor = MethodInterceptor { targetObject, targetMethod, methodParams, methodProxy ->
            AspectContext(targetClass, targetObject, targetMethod, methodProxy, methodParams, beanIdToAspectSetMap[beanId]!!).proceed()
        }
        return Enhancer.create(targetClass,methodInterceptor )
    }
}