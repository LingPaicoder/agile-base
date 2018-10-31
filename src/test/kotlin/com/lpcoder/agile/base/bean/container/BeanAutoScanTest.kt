package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.AutoInjectByConstructorBean
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import org.junit.Test

class BeanAutoScanTest {

    @Test
    fun testAutoScan() {
        println(AutoInjectByConstructorBean::class.java.constructors.any { it.isAnnotationPresent(AutoInject::class.java) })
        val yamlConfigPath = "bean/auto-scan-bean.yaml"
        DefaultBeanContainer(yamlConfigPath)
    }

}