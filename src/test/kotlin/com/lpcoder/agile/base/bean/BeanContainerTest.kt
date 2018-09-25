package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.exception.BeanCreationException
import com.lpcoder.agile.base.bean.parser.XMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.parser.YAMLBeanDefinitionParser
import com.lpcoder.agile.base.bean.service.CustomService
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class BeanContainerTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val beanDefinitionListStr = "[" +
            "BeanDefinition" +
            "(id=customService, " +
            "beanClassName=com.lpcoder.agile.base.bean.service.CustomService, " +
            "isSingleton=true), " +
            "BeanDefinition" +
            "(id=invalidBean, " +
            "beanClassName=com.lpcoder.agile.invalid.invalidBean, " +
            "isSingleton=false)]"

    private val errXmlConfigPath = "XXX.xml"
    private val xmlConfigPath = "agile-bean.xml"
    private val yamlConfigPath = "agile-bean.yaml"

    private val errBeanId = "invalidBean"
    private val beanId = "customService"
    private val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"


    @Test
    fun testYAMLParser() {
        assertEquals(beanDefinitionListStr, YAMLBeanDefinitionParser()
                .parse(ClassPathResource(yamlConfigPath))
                .toString())
    }

    @Test
    fun testXMLParser() {
        assertEquals(beanDefinitionListStr, XMLBeanDefinitionParser()
                .parse(ClassPathResource(xmlConfigPath))
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

    @Test
    fun testBeanCreationException() {
        thrown.expect(BeanCreationException::class.java)
        thrown.expectMessage("create bean for com.lpcoder.agile.invalid.invalidBean failed")
        DefaultBeanContainer(ClassPathResource(xmlConfigPath))
                .getBean(errBeanId) as? CustomService
    }

    /*@Test
    fun testBeanDefinitionStoreException() {
        //thrown.expect(BeanCreationException::class.java)
        //thrown.expectMessage("create bean for com.lpcoder.agile.invalid.invalidBean failed")
        DefaultBeanContainer(ClassPathResource(errXmlConfigPath))
                .getBean(errBeanId) as? CustomService
    }*/

}