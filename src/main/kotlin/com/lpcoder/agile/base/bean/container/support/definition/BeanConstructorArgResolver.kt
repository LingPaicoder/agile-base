package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer

class BeanConstructorArgResolver(private val container: BeanContainer) {

    fun newInstanceByAutoWireConstructor(beanId: String): Any {
        return Any()
    }

}