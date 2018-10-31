package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.BASIC_TYPE
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.RUNTIME_BEAN_REFERENCE_TYPE
import com.lpcoder.agile.base.util.ClassUtil.loadClass
import org.apache.commons.beanutils.ConvertUtilsBean


class BeanConstructorArgResolver(private val container: BeanContainer) {

    private val convertUtilsBean = ConvertUtilsBean()

    fun newInstanceByAutoWireConstructor(beanId: String): Any {
        val types = container.getBeanDefinition(beanId).constructorArgs.map { it.type }
        val argsToUse = container.getBeanDefinition(beanId).constructorArgs.map {
            when (it.value.type) {
                RUNTIME_BEAN_REFERENCE_TYPE -> BeanPropertyValueConverter(container).convert(it.value)
                BASIC_TYPE -> convertUtilsBean.lookup(loadClass(it.type)).convert(loadClass(it.type), it.value.value)
            }
        }
        val constructorToUse = container.getBeanClass(beanId).constructors
                .find { it.parameterTypes.toList().map { it.name } == types }
                ?: throw IllegalArgumentException("$beanId can't find a matched constructor")
        return constructorToUse.newInstance(*argsToUse.toTypedArray())
    }

}