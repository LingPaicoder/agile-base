package com.lpcoder.agile.base.core.resource

import com.lpcoder.agile.base.util.StringUtil
import java.io.InputStream

interface Resource {
    val filePath: String
    fun getInputStream(): InputStream
    fun getFileSuffix(): String {
        val suffix = filePath.substringAfterLast(".")
        if (StringUtil.isEmpty(suffix)) {
            throw IllegalArgumentException(
                    "can't identify suffix from filePath: " + filePath)
        }
        return suffix
    }
}