package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.parser.XMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.parser.YAMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.service.CustomService
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanContainerTest {

    private val beanDefinitionListStr = "[" +
            "BeanDefinition" +
            "(id=customService, " +
            "beanClassName=com.lpcoder.agile.base.bean.service.CustomService, " +
            "isSingleton=true), " +
            "BeanDefinition" +
            "(id=invalidBean, " +
            "beanClassName=com.lpcoder.agile.invalid.invalidBean, " +
            "isSingleton=false)]"
    val xmlConfigPath = "agile-bean.xml"
    val yamlConfigPath = "agile-bean.yaml"
    val beanId = "customService"
    val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"


    @Test
    fun testYAMLParser() {
        assertEquals(beanDefinitionListStr, YAMLBeanDefinitionParser()
                .parse(ClassPathResource("agile-bean.yaml"))
                .toString())
    }

    @Test
    fun testXMLParser() {
        assertEquals(beanDefinitionListStr, XMLBeanDefinitionParser()
                .parse(ClassPathResource("agile-bean.xml"))
                .toString())
    }

    @Test
    fun testGetBeanByXML() {
        val beanConfig = ClassPathResource(xmlConfigPath)
        val beanContainer = DefaultBeanContainer(beanConfig)
        val beanDefinition = beanContainer.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = beanContainer.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

    @Test
    fun testGetBeanByYAML() {
        val beanConfig = ClassPathResource(yamlConfigPath)
        val beanContainer = DefaultBeanContainer(beanConfig)
        val beanDefinition = beanContainer.getBeanDefinition(beanId)
        assertEquals(beanDefinition?.beanClassName, beanClassName)

        val customService = beanContainer.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

}