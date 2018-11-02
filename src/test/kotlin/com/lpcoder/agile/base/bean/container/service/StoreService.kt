package com.lpcoder.agile.base.bean.container.service

import com.lpcoder.agile.base.bean.container.dao.AccountDao
import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import com.lpcoder.agile.base.bean.container.support.annotation.Bean

@Bean
class StoreService {

    @AutoInject
    var accountDao: AccountDao? = null

    fun placeOrder() = println("place order")

}