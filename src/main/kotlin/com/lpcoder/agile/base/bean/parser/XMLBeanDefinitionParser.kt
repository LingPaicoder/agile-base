package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler.beNotNull
import com.lpcoder.agile.base.core.resource.Resource

class XMLBeanDefinitionParser : BeanDefinitionParser {
    override fun parse(source: Resource): List<BeanDefinition> {
        source must beNotNull
        source.getInputStream() must beNotNull
        return emptyList()
    }
}