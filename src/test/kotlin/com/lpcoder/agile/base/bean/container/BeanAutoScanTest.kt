package com.lpcoder.agile.base.bean.container

import com.lpcoder.agile.base.bean.container.support.DefaultBeanContainer
import org.junit.Test

class BeanAutoScanTest {

    @Test
    fun testAutoScan() {
        val yamlConfigPath = "bean/auto-scan-bean.yaml"
        DefaultBeanContainer(yamlConfigPath)
    }

}