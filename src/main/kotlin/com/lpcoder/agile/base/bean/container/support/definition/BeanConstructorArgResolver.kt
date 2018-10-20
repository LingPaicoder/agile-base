package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import org.apache.commons.beanutils.ConvertUtilsBean

class BeanConstructorArgResolver(private val container: BeanContainer) {

    private val convertUtil = ConvertUtilsBean()

    fun newInstanceByAutoWireConstructor(beanId: String): Any {
        val args = container.getBeanDefinition(beanId).constructorArgs.map {
            val type = container.getBeanClassLoader().loadClass(it.type)
            val value = BeanPropertyValueConverter(container).convert(it.value)
            convertUtil.lookup(type).convert(type, value)
        }
        val constructorToUse = container.getBeanClass(beanId).constructors
                .find { allTypeMatchedOnTheIndex(it.parameterTypes.toList(), args.map { it::class.java }) }
                ?: throw IllegalArgumentException("$beanId can't find a matched constructor")
        return constructorToUse.newInstance(args)
    }

    private fun allTypeMatchedOnTheIndex(norm: List<Class<*>>, target: List<Class<*>>): Boolean {
        if (norm.size != target.size) return false
        norm.forEachIndexed { i, clazz ->
            if (clazz !== target[i]) return false
        }
        return true
    }

}