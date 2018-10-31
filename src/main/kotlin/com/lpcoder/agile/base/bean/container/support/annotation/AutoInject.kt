package com.lpcoder.agile.base.bean.container.support.annotation

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
// todo: 自定义bean_id
annotation class AutoInject