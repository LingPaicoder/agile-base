package com.lpcoder.agile.base.model.builder.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Join(val field: String = "")