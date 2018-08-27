package com.lpcoder.agile.base.bean.factory.support

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.bean.factory.BeanFactory

class DefaultBeanFactory(configPath: String) : BeanFactory {

    override fun getBeanDefinition(beanId: String): BeanDefinition? {
        return null
    }

    override fun getBean(beanId: String): Any? {
        return null
    }

}