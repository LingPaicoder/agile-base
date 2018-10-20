package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer

class BeanConstructorArgResolver(private val container: BeanContainer) {

    fun newInstanceByAutoWireConstructor(beanId: String): Any {
        val contractorArgs = container.getBeanDefinition(beanId).constructorArgs
        contractorArgs.sortBy { it.index }
        /*container.getBeanClass(beanId).constructors
                .filter { it.parameterTypes.size == contractorArgs.size }
                .filter { }*/
        return Any()
    }

}