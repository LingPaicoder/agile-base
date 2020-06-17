package com.lpcoder.agile.base.model.builder

/**
 * 1. ModelBuilderDelegate有内存泄露风险，解决方案有两个：
 *      1). 反射动态添加字段
 *      2). 弱引用避免阻碍垃圾回收
 * @author liurenpeng
 * Created on 2020-06-17
 */