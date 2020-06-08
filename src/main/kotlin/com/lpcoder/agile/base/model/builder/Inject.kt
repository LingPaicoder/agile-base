package com.lpcoder.agile.base.model.builder

/**
 * 注入
 * 用于相对独立的数据
 * 例如当前登录的用户id
 * GlobalScope inject USER_ID by ::visitor
 * @author liurenpeng
 * Created on 2020-06-04
 */

class InjectHolder<out P>(injectPoint: P) : OpenSingle<P>(injectPoint)

object GlobalScope {
    val holderToInjectorMap = mutableMapOf<InjectHolder<*>, Any>()
}

infix fun <P> GlobalScope.inject(injectPoint: P) = InjectHolder(injectPoint)

infix fun <T> InjectHolder<String>.by(injector: () -> T) {
    GlobalScope.holderToInjectorMap[this] = injector
}

@Suppress("UNCHECKED_CAST")
infix fun <T, P> GlobalScope.extract(injectPoint: P) : T {
    val injector = holderToInjectorMap[InjectHolder(injectPoint)] as (() -> T)
    return injector.invoke()
}