package com.lpcoder.agile.base.core.resource

import com.lpcoder.agile.base.util.StringUtil
import java.io.InputStream

interface Resource {
    val filePath: String
    fun getInputStream(): InputStream
    fun getFileSuffix(): String {
        val suffix = filePath.substringAfterLast(".")
        if (suffix.isBlank()) {
            throw IllegalArgumentException(
                    "can't identify suffix from filePath: " + filePath)
        }
        return suffix
    }
}