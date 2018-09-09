package com.lpcoder.agile.base.bean.definition.support.resource

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class FileSystemBeanDefinitionResource(override val path: String)
    : BeanDefinitionResource {

    override fun getInputStream(): InputStream {
        return FileInputStream(File(this.path))
    }
}