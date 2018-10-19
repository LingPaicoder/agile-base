package com.lpcoder.agile.base.bean.container.service

import com.lpcoder.agile.base.bean.container.dao.AccountDao
import com.lpcoder.agile.base.bean.container.dao.ItemDao

class CustomService {
    var accountDao: AccountDao? = null
    var itemDao: ItemDao? = null
    var author: String? = null
    var age: Int? = null
}
