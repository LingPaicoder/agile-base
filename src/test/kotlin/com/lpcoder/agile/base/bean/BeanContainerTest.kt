package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.exception.BeanCreationException
import com.lpcoder.agile.base.bean.exception.BeanDefinitionStoreException
import com.lpcoder.agile.base.bean.service.CustomService
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class BeanContainerTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    private val errXmlConfigPath = "XXX.xml"
    private val xmlConfigPath = "agile-bean.xml"
    private val yamlConfigPath = "agile-bean.yaml"

    private val errAndNotSingletonBeanId = "invalidBean"
    private val beanId = "customService"
    private val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

    private var containerOfYAML: BeanContainer? = null
    private var containerOfXML: BeanContainer? = null

    @Before
    fun before() {
        containerOfYAML = DefaultBeanContainer(ClassPathResource(yamlConfigPath))
        containerOfXML = DefaultBeanContainer(ClassPathResource(xmlConfigPath))
    }

    @Test
    fun testParser() {
        testParser(containerOfYAML!!)
        testParser(containerOfXML!!)
    }

    private fun testParser(container: BeanContainer) {
        val beanDef = container.getBeanDefinition(beanId)!!
        assertEquals(beanId, beanDef.id)
        assertEquals(beanClassName, beanDef.beanClassName)
        assertTrue(beanDef.isSingleton)
        val beanNotSingletonDef = container.getBeanDefinition(errAndNotSingletonBeanId)!!
        assertFalse(beanNotSingletonDef.isSingleton)
    }

    @Test
    fun testGetBeanByXML() {
        testGetBean(containerOfYAML!!)
        testGetBean(containerOfXML!!)
    }

    private fun testGetBean(container: BeanContainer) {
        val customService = container.getBean(beanId) as? CustomService
        assertNotNull(customService)
    }

    @Test
    fun testSingleton() {
        val customService1 = containerOfXML!!.getBean(beanId)
        val customService2 = containerOfXML!!.getBean(beanId)
        assertTrue(customService1 === customService2)
    }

    @Test
    fun testBeanCreationException() {
        thrown.expect(BeanCreationException::class.java)
        thrown.expectMessage("create bean for com.lpcoder.agile.invalid.invalidBean failed")
        containerOfXML!!.getBean(errAndNotSingletonBeanId) as? CustomService
    }

    @Test
    fun testBeanDefinitionStoreException() {
        thrown.expect(BeanDefinitionStoreException::class.java)
        thrown.expectMessage("XXX.xml cannot be opened")
        DefaultBeanContainer(ClassPathResource(errXmlConfigPath))
                .getBean(beanId) as? CustomService
    }

}