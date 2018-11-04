package com.lpcoder.agile.base.bean.container.support.aspect

abstract class MatchByAnnotationAspect<T, R>(order: Int = 1,
                                             classAnnotationClass: Class<out Annotation>,
                                             methodAnnotationClass: Class<out Annotation>)
    : BaseAspect<T, R>(order,
        { method ->
            method.declaringClass.isAnnotationPresent(classAnnotationClass)
                    && method.isAnnotationPresent(methodAnnotationClass)
        })