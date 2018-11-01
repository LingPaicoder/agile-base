package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.AutoInjectByConstructorBean
import com.lpcoder.agile.base.bean.container.component.AutoInjectBySetterBean
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanAutoScanTest {

    @Test
    fun testAutoScan() {
        val yamlConfigPath = "bean/auto-scan-bean.yaml"
        val container = DefaultBeanContainer(yamlConfigPath)
        val autoInjectByConstructorBean = container.getBean("autoInjectByConstructorBean")
                as AutoInjectByConstructorBean
        val autoInjectBySetterBean = container.getBean("autoInjectBySetterBean")
                as AutoInjectBySetterBean
        assertNotNull(autoInjectByConstructorBean.autoInjectDao)
        assertNotNull(autoInjectBySetterBean.autoInjectDao)
    }

}