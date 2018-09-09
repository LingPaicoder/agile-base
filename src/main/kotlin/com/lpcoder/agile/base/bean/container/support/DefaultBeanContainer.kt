package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.definition.BeanDefinition

class DefaultBeanContainer(configPath: String) : BeanContainer {

    override fun getBeanDefinition(beanId: String): BeanDefinition? {
        return null
    }

    override fun getBean(beanId: String): Any? {
        return null
    }

}