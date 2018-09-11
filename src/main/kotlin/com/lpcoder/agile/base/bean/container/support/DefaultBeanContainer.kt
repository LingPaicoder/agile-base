package com.lpcoder.agile.base.bean.container.support

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.core.resource.Resource

class DefaultBeanContainer(resource: Resource) : BeanContainer {

    override fun getBeanDefinition(beanId: String): BeanDefinition? {
        return null
    }

    override fun getBean(beanId: String): Any? {
        return null
    }

}