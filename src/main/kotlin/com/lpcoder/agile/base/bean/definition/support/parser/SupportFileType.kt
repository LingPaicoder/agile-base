package com.lpcoder.agile.base.bean.definition.support.parser

import com.lpcoder.agile.base.bean.exception.BeanDefinitionStoreException
import org.apache.commons.lang3.StringUtils

enum class SupportFileType(val suffix: String, val parser: BeanDefinitionParser) {
    XML("xml", XMLBeanDefinitionParser()),
    YAML("yaml", YAMLBeanDefinitionParser());

    companion object {
        fun getBySuffix(suffix: String): SupportFileType {
            return try {
                enumValues<SupportFileType>().first { fileType -> suffix == fileType.suffix }
            } catch (e: NoSuchElementException) {
                throw BeanDefinitionStoreException(StringUtils.join(
                        "unrecognizable file suffix: ",
                        suffix,
                        ". all of the support suffix: ",
                        getAllSupportSuffix().toString()
                ), e)
            }
        }

        private fun getAllSupportSuffix(): List<String> {
            return enumValues<SupportFileType>().map { fileType -> fileType.suffix }
        }
    }
}