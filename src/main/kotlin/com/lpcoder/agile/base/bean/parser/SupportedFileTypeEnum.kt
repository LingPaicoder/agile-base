package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.exception.BeanDefinitionException
import org.apache.commons.lang3.StringUtils

enum class SupportedFileTypeEnum(val suffix: String, val parser: BeanDefinitionParser) {
    XML("xml", XMLBeanDefinitionParser()),
    YAML("yaml", YAMLBeanDefinitionParser());

    companion object {
        fun getBySuffix(suffix: String): SupportedFileTypeEnum {
            return try {
                enumValues<SupportedFileTypeEnum>().first { fileType -> suffix == fileType.suffix }
            } catch (e: NoSuchElementException) {
                throw BeanDefinitionException(StringUtils.join(
                        "unrecognizable file suffix: ",
                        suffix,
                        ". all of the support suffix: ",
                        getAllSupportSuffix().toString()
                ), e)
            }
        }

        private fun getAllSupportSuffix(): List<String> {
            return enumValues<SupportedFileTypeEnum>().map { fileType -> fileType.suffix }
        }
    }
}