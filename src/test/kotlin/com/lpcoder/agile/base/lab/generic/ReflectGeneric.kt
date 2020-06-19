package com.lpcoder.agile.base.lab.generic

import kotlin.reflect.KTypeParameter

/**
 * @author liurenpeng
 * Created on 2020-06-19
 */

/**
 * 声明一个父类 BaseContainer
 */
open class BaseContainer<T>

/**
 * 声明一个Container 继承BaseContainer
 */
class Container<T : Comparable<T>> : BaseContainer<Int> {
    val elements: MutableList<T>

    constructor(elements: MutableList<T>) : super() {
        this.elements = elements
    }

    fun sort(): Container<T> {
        elements.sort()
        return this
    }

    override fun toString(): String {
        return "Container(elements=$elements)"
    }

}

object Test {


    @JvmStatic
    fun main(args: Array<String>) {
        //声明一个Container实例
        val container = Container(mutableListOf(1, 2, 3, 4, 5))
        // 获取Container的KClass对象引用
        val kClazz = container::class
        // KClass对象的typeParameters属性中存有类型参数的信息
        val typeParameters = kClazz.typeParameters
        //typeParameters 取数组的第一个
        val kTypeParameter: KTypeParameter = typeParameters[0]
        // kTypeParameter有下面等属性
        println(kTypeParameter.isReified) // false
        println(kTypeParameter.name) // T
        println(kTypeParameter.upperBounds) // [kotlin.Comparable<T>]
        println(kTypeParameter.variance) // INVARIANT

        val constructors = kClazz.constructors
        for (KFunction in constructors) {
            KFunction.parameters.forEach {
                val name = it.name
                val type = it.type
                println("name=$name") // name=elements
                println("type=$type") // type=kotlin.collections.MutableList<T>
                for (KTypeProjection in type.arguments) {
                    println(KTypeProjection.type) // T
                }
            }
        }
    }
}