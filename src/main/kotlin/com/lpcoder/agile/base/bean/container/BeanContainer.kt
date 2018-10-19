package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition

interface BeanContainer {
    fun getBeanDefinition(beanId: String): BeanDefinition
    fun getBean(beanId: String): Any
}