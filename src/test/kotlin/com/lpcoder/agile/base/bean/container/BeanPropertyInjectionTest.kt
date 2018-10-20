package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.SimpleTypeBean
import com.lpcoder.agile.base.bean.container.service.CustomService
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import org.junit.Assert
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
        Assert.assertNotNull(customService.accountDao)
        Assert.assertNotNull(customService.itemDao)
        Assert.assertEquals("lingPai", customService.author)
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
        Assert.assertEquals('c', simpleTypeBean.charVal)
        Assert.assertEquals("str", simpleTypeBean.strVal)
        Assert.assertEquals(10.toByte(), simpleTypeBean.byteVal)
        Assert.assertEquals(10.toShort(), simpleTypeBean.shortVal)
        Assert.assertEquals(10, simpleTypeBean.intVal)
        Assert.assertEquals(10.toLong(), simpleTypeBean.longVal)
        Assert.assertEquals(2.1.toFloat(), simpleTypeBean.floatVal)
        Assert.assertEquals(2.1, simpleTypeBean.doubleVal)
        Assert.assertEquals(true, simpleTypeBean.booleanTrueVal)
        Assert.assertEquals(false, simpleTypeBean.booleanFalseVal)
    }

}