package com.lpcoder.agile.base.bean.container

import org.junit.Assert.assertTrue
import org.junit.Test

class BeanSingletonTest : BaseTest() {

    @Test
    fun testSingleton() {
        val customService1 = containerOfXML!!.getBean(beanId)
        val customService2 = containerOfXML!!.getBean(beanId)
        assertTrue(customService1 === customService2)
    }

}