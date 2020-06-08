package com.lpcoder.agile.base.model.builder

import kotlin.reflect.KClass

/**
 * 绑定
 * 用于数据独立于model之外的场景，或查询时成本较高需要lazyBuild的场景
 * 例如文章的点赞数
 * Article::class bind LIKE_COUNT by ::getArticleLikeCount
 * @author liurenpeng
 * Created on 2020-06-04
 */

class BindHolder<out A, out B>(first: A, second: B) : OpenPair<A, B>(first, second)

infix fun <T: Any> KClass<T>.bind(bindPoint: String) = BindHolder(this, bindPoint)

infix fun <T: Any, I, R> BindHolder<KClass<T>, String>.by(binder: (Collection<I>) -> Map<I, R>) {
    return
}

infix fun <T: Any, R> KClass<T>.extract(injectPoint: String) : R {
    return "0" as R
}