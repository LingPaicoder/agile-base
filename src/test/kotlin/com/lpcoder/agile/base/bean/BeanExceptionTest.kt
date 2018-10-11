package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.exception.BeanDefinitionException
import com.lpcoder.agile.base.bean.service.CustomService
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Test

class BeanExceptionTest : BaseTest() {

    private val errXmlConfigPath = "XXX.xml"
    private val errAndNotSingletonBeanId = "invalidBean"

    @Test
    fun testBeanCreationExceptionCausedByClass() {
        thrown.expect(BeanDefinitionException::class.java)
        thrown.expectMessage("create bean for com.lpcoder.agile.invalid.invalidBean failed")
        containerOfXML!!.getBean(errAndNotSingletonBeanId) as? CustomService
    }

    @Test
    fun testBeanDefinitionExceptionCausedByConfFile() {
        thrown.expect(BeanDefinitionException::class.java)
        thrown.expectMessage("XXX.xml cannot be opened")
        DefaultBeanContainer(ClassPathResource(errXmlConfigPath))
                .getBean(beanId) as? CustomService
    }

}