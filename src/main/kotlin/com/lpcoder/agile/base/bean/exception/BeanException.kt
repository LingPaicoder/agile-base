package com.lpcoder.agile.base.bean.exception

class BeanException(msg: String, e: Throwable? = null) : RuntimeException(msg, e) {
}