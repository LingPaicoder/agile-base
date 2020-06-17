package com.lpcoder.agile.base.model.builder

import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */
object BuildContext {
    val indexerHolder = mutableMapOf<KClass<*>, Any>()
    val builderHolder = mutableMapOf<KClass<*>, Any>()
    val joinHolder = mutableMapOf<KClass<*>, MutableMap<Any, Any>>()
    val outJoinHolder = mutableMapOf<KClass<*>, MutableMap<Any, Any>>()
    val singleMapHolder = mutableMapOf<KClass<*>, MutableMap<Any, Any>>()
    val multiMapHolder = mutableMapOf<KClass<*>, MutableMap<Any, Any>>()
}