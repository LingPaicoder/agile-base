package com.lpcoder.agile.base.bean.exception

open class BeanException(msg: String, e: Throwable? = null)
    : RuntimeException(msg, e)

