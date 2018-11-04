package com.lpcoder.agile.base.bean.container.support.definition

import com.lpcoder.agile.base.bean.container.BeanContainer
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.BASIC_TYPE
import com.lpcoder.agile.base.bean.container.support.definition.BeanPropertyValueType.RUNTIME_BEAN_REFERENCE_TYPE
import com.lpcoder.agile.base.util.ClassUtil.loadClass
import org.apache.commons.beanutils.ConvertUtilsBean
import java.lang.reflect.Constructor


class BeanConstructorResolver(private val container: BeanContainer) {

    private val convertUtilsBean = ConvertUtilsBean()

    fun newInstanceByAutoWireConstructor(beanId: String): Any =
            getConstructor(beanId).newInstance(*getConstructorArgs(beanId))

    fun getConstructor(beanId: String): Constructor<*> {
        val types = container.getBeanDefinition(beanId).constructorArgs.map { it.type }
        return container.getBeanClass(beanId).constructors
                .find { constructor -> constructor.parameterTypes.toList().map { it.name } == types }
                ?: throw IllegalArgumentException("$beanId can't find a matched constructor")
    }

    fun getConstructorArgs(beanId: String): Array<Any> {
        return container.getBeanDefinition(beanId).constructorArgs.map {
            when (it.value.type) {
                RUNTIME_BEAN_REFERENCE_TYPE -> BeanPropertyValueConverter(container).convert(it.value)
                BASIC_TYPE -> convertUtilsBean.lookup(loadClass(it.type)).convert(loadClass(it.type), it.value.value)
            }
        }.toTypedArray()
    }

}