package com.lpcoder.agile.base.bean.parser

import com.lpcoder.agile.base.bean.definition.BeanDefinition
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.support.AnyRuler
import com.lpcoder.agile.base.core.resource.Resource
import org.yaml.snakeyaml.Yaml

class YAMLBeanDefinitionParser : BeanDefinitionParser {

    override fun parse(source: Resource): List<BeanDefinition> {
        source must AnyRuler.beNotNull
        source.getInputStream() must AnyRuler.beNotNull

        @Suppress("UNCHECKED_CAST")
        val map = Yaml().load(source.getInputStream())
                as Map<String, List<Map<String, Any>>>
        return map[beansAttr]!!.map { entry ->
            val id = entry[idAttr]!! as String
            val clazz = entry[classAttr]!! as String
            val isSingleton = (entry[isSingletonAttr] ?: true) as Boolean
            return@map BeanDefinition(id, clazz, isSingleton)
        }
    }
}