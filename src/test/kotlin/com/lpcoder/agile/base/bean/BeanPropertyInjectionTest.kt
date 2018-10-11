package com.lpcoder.agile.base.bean

import com.lpcoder.agile.base.bean.service.CustomService
import org.junit.Assert
import org.junit.Test

class BeanPropertyInjectionTest : BaseTest() {

    @Test
    fun testPropNotNull() {
        val customService = containerOfYAML?.getBean(beanId) as? CustomService
        Assert.assertNotNull(customService?.accountDao)
        Assert.assertNotNull(customService?.itemDao)
    }

}