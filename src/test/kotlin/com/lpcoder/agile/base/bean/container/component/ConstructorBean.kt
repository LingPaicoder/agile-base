package com.lpcoder.agile.base.bean.container.component

import com.lpcoder.agile.base.bean.container.dao.AccountDao
import com.lpcoder.agile.base.bean.container.dao.ItemDao

class ConstructorBean(val accountDao: AccountDao,
                      val itemDao: ItemDao,
                      val version: Int) {

    constructor(accountDao: AccountDao, itemDao: ItemDao)
            : this(accountDao, itemDao, -1)
}