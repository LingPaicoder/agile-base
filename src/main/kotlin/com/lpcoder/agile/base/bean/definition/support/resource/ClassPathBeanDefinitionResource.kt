package com.lpcoder.agile.base.bean.definition.support.resource

import com.lpcoder.agile.base.util.ClassUtil
import java.io.FileNotFoundException
import java.io.InputStream

class ClassPathBeanDefinitionResource(override val path: String)
    : BeanDefinitionResource {

    private var classLoader: ClassLoader = ClassUtil.getDefaultClassLoader()

    constructor(path: String, classLoader: ClassLoader) : this(path) {
        this.classLoader = classLoader
    }

    override fun getInputStream(): InputStream {
        return this.classLoader.getResourceAsStream(this.path)
                ?: throw FileNotFoundException(path + " cannot be opened")
    }
}