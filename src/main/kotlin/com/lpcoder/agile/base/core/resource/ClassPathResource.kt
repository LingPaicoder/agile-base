package com.lpcoder.agile.base.core.resource

import com.lpcoder.agile.base.bean.container.support.exception.BeanDefinitionException
import com.lpcoder.agile.base.util.ClassUtil
import java.io.InputStream

class ClassPathResource(override val filePath: String,
                        private var classLoader: ClassLoader)
    : Resource {

    constructor(path: String) : this(path, ClassUtil.getDefaultClassLoader())

    override fun getInputStream(): InputStream {
        return this.classLoader.getResourceAsStream(this.filePath)
                ?: throw BeanDefinitionException(filePath + " cannot be opened")
    }
}