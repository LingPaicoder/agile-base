package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.service.CustomService
import org.junit.Assert.*
import org.junit.Test

class BeanContainerParserTest : BaseTest() {

    @Test
    fun testParser() {
        testParser(containerOfYAML!!)
        testParser(containerOfXML!!)
    }

    private fun testParser(container: BeanContainer) {
        val beanDef = container.getBeanDefinition(beanId)
        assertEquals(beanId, beanDef.id)
        assertEquals(beanClassName, beanDef.beanClassName)
        assertTrue(beanDef.isSingleton)
        val beanNotSingletonDef = container.getBeanDefinition(notSingletonBeanId)
        assertFalse(beanNotSingletonDef.isSingleton)
    }

    @Test
    fun testGetBeanByXML() {
        containerOfYAML!!.getBean(beanId) as CustomService
        containerOfXML!!.getBean(beanId) as CustomService
    }

}