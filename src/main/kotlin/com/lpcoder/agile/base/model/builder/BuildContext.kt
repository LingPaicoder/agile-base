package com.lpcoder.agile.base.model.builder

import kotlin.reflect.KClass

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */
object BuildContext {
    val indexerMap = mutableMapOf<KClass<*>, Any>()
    val builderMap = mutableMapOf<KClass<*>, Any>()
}