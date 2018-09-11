package com.lpcoder.agile.base.core.resource

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class FileSystemResource(override val filePath: String)
    : Resource {

    override fun getInputStream(): InputStream {
        return FileInputStream(File(this.filePath))
    }
}