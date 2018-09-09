package com.lpcoder.agile.base.bean.definition

import com.fasterxml.jackson.annotation.JsonProperty

data class BeanDefinition(
        @JsonProperty(value = "id")
        val id: String,
        @JsonProperty(value = "class")
        val beanClassName: String,
        @JsonProperty(value = "isSingleton")
        val isSingleton: Boolean
)