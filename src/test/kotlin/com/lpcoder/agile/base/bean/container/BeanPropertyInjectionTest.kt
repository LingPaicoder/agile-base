package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.SimpleTypeBean
import com.lpcoder.agile.base.bean.container.service.CustomService
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import org.junit.Assert.*
import org.junit.Test

class BeanPropertyInjectionTest : BaseTest() {

    @Test
    fun testCommonProp() {
        var customService = containerOfYAML?.getBean(beanId) as? CustomService
        testCommonProp(customService!!)
        customService = containerOfXML?.getBean(beanId) as? CustomService
        testCommonProp(customService!!)
    }

    private fun testCommonProp(customService: CustomService) {
        assertNotNull(customService.accountDao)
        assertNotNull(customService.itemDao)
        assertEquals("lingPai", customService.author)
    }

    @Test
    fun testSimpleTypeProp() {
        val yamlConfigPath = "bean/simple-type-bean.yaml"
        var simpleTypeBean = DefaultBeanContainer(yamlConfigPath)
                .getBean("simpleTypeBean")as? SimpleTypeBean
        testSimpleTypeProp(simpleTypeBean!!)

        val xmlConfigPath = "bean/simple-type-bean.xml"
        simpleTypeBean = DefaultBeanContainer(xmlConfigPath)
                .getBean("simpleTypeBean")as? SimpleTypeBean
        testSimpleTypeProp(simpleTypeBean!!)
    }

    private fun testSimpleTypeProp(simpleTypeBean: SimpleTypeBean) {
        assertEquals('c', simpleTypeBean.charVal)
        assertEquals("str", simpleTypeBean.strVal)
        assertEquals(10.toByte(), simpleTypeBean.byteVal)
        assertEquals(10.toShort(), simpleTypeBean.shortVal)
        assertEquals(10, simpleTypeBean.intVal)
        assertEquals(10.toLong(), simpleTypeBean.longVal)
        assertEquals(2.1.toFloat(), simpleTypeBean.floatVal)
        assertEquals(2.1, simpleTypeBean.doubleVal)
        assertEquals(true, simpleTypeBean.booleanTrueVal)
        assertEquals(false, simpleTypeBean.booleanFalseVal)
    }

}