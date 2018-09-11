package com.lpcoder.agile.base.core.resource

import com.lpcoder.agile.base.util.ClassUtil
import java.io.FileNotFoundException
import java.io.InputStream

class ClassPathResource(override val filePath: String)
    : Resource {

    private var classLoader: ClassLoader = ClassUtil.getDefaultClassLoader()

    constructor(path: String, classLoader: ClassLoader) : this(path) {
        this.classLoader = classLoader
    }

    override fun getInputStream(): InputStream {
        return this.classLoader.getResourceAsStream(this.filePath)
                ?: throw FileNotFoundException(filePath + " cannot be opened")
    }
}