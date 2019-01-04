package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.support.aspect.AbstractAspect
import com.lpcoder.agile.base.bean.container.support.definition.BeanDefinition

// todo : bean生命周期的管理
// todo : 循环依赖的检查
interface BeanContainer {
    fun getBean(beanId: String): Any
    fun getBeanDefinition(beanId: String): BeanDefinition
    fun getBeanClass(beanId: String): Class<*>
    fun getBeanClassLoader(): ClassLoader
    fun getBeanDefinitions(): Set<BeanDefinition>
    fun getAspects(): Set<AbstractAspect>
}