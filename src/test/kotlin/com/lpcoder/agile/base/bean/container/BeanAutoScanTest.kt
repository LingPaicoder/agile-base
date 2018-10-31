package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.component.AutoInjectByConstructorBean
import com.lpcoder.agile.base.bean.container.component.AutoInjectBySetterBean
import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import org.junit.Assert.assertNotNull
import org.junit.Test

class BeanAutoScanTest {

    @Test
    fun testAutoScan() {
        AutoInjectBySetterBean::class.java.declaredFields.forEach {
            println("boolean:${it.isAnnotationPresent(AutoInject::class.java)}")
            println("name:${it.name}.--${it.declaredAnnotations.size} ")
            it.declaredAnnotations.forEach { println("annotation:${it::class}") }
        }
        println("---")

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