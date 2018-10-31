package com.lpcoder.agile.base.bean.container.support.definition

data class BeanDefinition(
        val id: String,
        val beanClassName: String,
        val isSingleton: Boolean,
        val constructorArgs: MutableList<BeanConstructorArg> = mutableListOf(),
        val properties: MutableList<BeanProperty> = mutableListOf()
)