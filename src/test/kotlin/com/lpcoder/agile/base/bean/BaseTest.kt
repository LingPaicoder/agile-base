package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.core.resource.ClassPathResource
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

open class BaseTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    protected val notSingletonBeanId = "notSingletonBean"
    protected val beanId = "customService"
    protected val beanClassName = "com.lpcoder.agile.base.bean.service.CustomService"

    protected var containerOfYAML: BeanContainer? = null
    protected var containerOfXML: BeanContainer? = null

    private val xmlConfigPath = "agile-bean.xml"
    private val yamlConfigPath = "agile-bean.yaml"

    @Before
    fun before() {
        containerOfYAML = DefaultBeanContainer(yamlConfigPath)
        containerOfXML = DefaultBeanContainer(xmlConfigPath)
    }

}