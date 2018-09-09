package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.definition.support.parser.XMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.definition.support.resource.ClassPathBeanDefinitionResource
import com.lpcoder.agile.base.bean.service.CustomService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanContainerTest {

    @Test
    fun test() {
        println(XMLBeanDefinitionParser()
                .parse(ClassPathBeanDefinitionResource("agile-bean.xml")))
    }

    @Test
    fun testGetBeanByXML() {
        val configPath = "agile-bean.xml"
        val beanId = "customService"
        val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

        val container: BeanContainer = DefaultBeanContainer(configPath)
        val beanDefinition = container.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = container.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

    @Test
    fun testGetBeanByYAML() {
        val configPath = "agile-bean.yaml"
        val beanId = "customService"
        val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

        val container: BeanContainer = DefaultBeanContainer(configPath)
        val beanDefinition = container.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = container.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

}