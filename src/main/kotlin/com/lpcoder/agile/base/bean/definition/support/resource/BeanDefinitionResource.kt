package com.lpcoder.agile.base.bean.definition.support.resource

import java.io.InputStream

interface BeanDefinitionResource {
    val path: String
    fun getInputStream(): InputStream
}