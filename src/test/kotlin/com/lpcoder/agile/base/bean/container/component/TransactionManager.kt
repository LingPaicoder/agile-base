package com.lpcoder.agile.base.bean.container.component

class TransactionManager {

    fun start() = println("start tx")
    fun commit() = println("commit tx")
    fun rollback() = println("rollback tx")

}