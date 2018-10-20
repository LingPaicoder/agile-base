package com.lpcoder.agile.base.bean.container

import org.junit.Assert.assertTrue
import org.junit.Test

class BeanSingletonTest : BaseTest() {

    @Test
    fun testSingleton() {
        var customService1 = containerOfXML!!.getBean(beanId)
        var customService2 = containerOfXML!!.getBean(beanId)
        assertTrue(customService1 === customService2)

        customService1 = containerOfYAML!!.getBean(beanId)
        customService2 = containerOfYAML!!.getBean(beanId)
        assertTrue(customService1 === customService2)
    }

}