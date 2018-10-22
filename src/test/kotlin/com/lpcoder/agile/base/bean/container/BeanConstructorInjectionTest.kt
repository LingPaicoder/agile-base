package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.ConstructorBean
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanConstructorInjectionTest {

    @Test
    fun testConstructorInjection() {
        val yamlConfigPath = "bean/constructor-bean.yaml"
        var constructorBean = DefaultBeanContainer(yamlConfigPath)
                .getBean("constructorBean")as? ConstructorBean
        testConstructorInjection(constructorBean!!)

        val xmlConfigPath = "bean/constructor-bean.xml"
        constructorBean = DefaultBeanContainer(xmlConfigPath)
                .getBean("constructorBean")as? ConstructorBean
        testConstructorInjection(constructorBean!!)
    }

    private fun testConstructorInjection(constructorBean: ConstructorBean) {
        assertNotNull(constructorBean.accountDao)
        assertNotNull(constructorBean.itemDao)
        assertEquals(1, constructorBean.version)
    }

}