package com.lpcoder.agile.base.bean.container.support.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Bean(val value: String = "")