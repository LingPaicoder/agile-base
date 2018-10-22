package com.lpcoder.agile.base.bean.container.component

import com.lpcoder.agile.base.bean.container.dao.AutoInjectDao
import com.lpcoder.agile.base.bean.container.support.annotation.AutoInject
import com.lpcoder.agile.base.bean.container.support.annotation.Bean

@Bean
class AutoInjectByConstructorBean(@AutoInject val autoInjectDao: AutoInjectDao)