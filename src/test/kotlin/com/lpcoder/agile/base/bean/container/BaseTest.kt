package com.lpcoder.agile.base.bean.container

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
    protected val beanClassName = "com.lpcoder.agile.base.bean.container.CustomService"

    protected var containerOfYAML: BeanContainer? = null
    protected var containerOfXML: BeanContainer? = null

    private val yamlConfigPath = "agile-bean.yaml"
    private val xmlConfigPath = "agile-bean.xml"

    @Before
    fun before() {
        containerOfYAML = DefaultBeanContainer(yamlConfigPath)
        containerOfXML = DefaultBeanContainer(xmlConfigPath)
    }

}