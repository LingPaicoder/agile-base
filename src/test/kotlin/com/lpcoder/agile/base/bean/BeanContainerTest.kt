package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.definition.support.parser.XMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.service.CustomService
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanContainerTest {

    @Test
    fun test() {
        println(XMLBeanDefinitionParser()
                .parse(ClassPathResource("agile-bean.xml")))
    }

    @Test
    fun testGetBeanByXML() {
        val configPath = "agile-bean.xml"
        val beanId = "customService"
        val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

        val beanConfig = ClassPathResource(configPath)
        val beanContainer = DefaultBeanContainer(beanConfig)
        val beanDefinition = beanContainer.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = beanContainer.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

    @Test
    fun testGetBeanByYAML() {
        val configPath = "agile-bean.yaml"
        val beanId = "customService"
        val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

        val beanConfig = ClassPathResource(configPath)
        val beanContainer = DefaultBeanContainer(beanConfig)
        val beanDefinition = beanContainer.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = beanContainer.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

}