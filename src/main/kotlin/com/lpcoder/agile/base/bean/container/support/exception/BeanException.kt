package com.lpcoder.agile.base.bean.container.support.exception

open class BeanException(msg: String, e: Throwable? = null)
    : RuntimeException(msg, e)

