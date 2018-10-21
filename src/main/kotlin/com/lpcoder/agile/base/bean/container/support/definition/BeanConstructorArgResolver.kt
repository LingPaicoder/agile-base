package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.support.RuntimeBeanReferenceValue
import com.lpcoder.agile.base.bean.container.support.definition.support.TypedStringValue
import com.lpcoder.agile.base.util.ClassUtil.loadClass
import org.apache.commons.beanutils.ConvertUtilsBean

class BeanConstructorArgResolver(private val container: BeanContainer) {

    private val convertUtilsBean = ConvertUtilsBean()

    fun newInstanceByAutoWireConstructor(beanId: String): Any {
        val types = container.getBeanDefinition(beanId).constructorArgs.map { it.type }
        val argsToUse = container.getBeanDefinition(beanId).constructorArgs.map {
            when (it.value) {
                is RuntimeBeanReferenceValue -> BeanPropertyValueConverter(container).convert(it.value)
                is TypedStringValue -> convertUtilsBean.lookup(loadClass(it.type)).convert(loadClass(it.type), it.value.value)
                else -> IllegalArgumentException("unsupported value type: ${it.value::class}")
            }
        }
        val constructorToUse = container.getBeanClass(beanId).constructors
                .find { it.parameterTypes.toList().map { it.name } == types }
                ?: throw IllegalArgumentException("$beanId can't find a matched constructor")
        return constructorToUse.newInstance(*argsToUse.toTypedArray())
    }

}