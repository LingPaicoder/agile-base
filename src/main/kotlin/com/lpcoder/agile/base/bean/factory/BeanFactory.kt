package com.lpcoder.agile.base.bean.factory

import com.lpcoder.agile.base.bean.definition.BeanDefinition

interface BeanFactory {
    fun getBeanDefinition(beanId: String): BeanDefinition?
    fun getBean(beanId: String): Any?
}