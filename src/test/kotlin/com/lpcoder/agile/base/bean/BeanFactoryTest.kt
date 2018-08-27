package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.factory.BeanFactory
import com.lpcoder.agile.base.bean.factory.support.DefaultBeanFactory
import com.lpcoder.agile.base.bean.service.CustomService
import org.junit.Assert.*
import org.junit.Test

class BeanFactoryTest {

    @Test
    fun testGetBean() {
        val configPath = "beans.xml"
        val beanId = "customService"
        val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

        val factory: BeanFactory = DefaultBeanFactory(configPath)
        val beanDefinition = factory.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = factory.getBean(beanId) as? CustomService
        assertNotNull(customService)

    }

}