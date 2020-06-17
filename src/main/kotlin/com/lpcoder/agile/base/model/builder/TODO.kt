package com.lpcoder.agile.base.model.builder

/**
 * 1. ModelBuilderDelegate有内存泄露风险，解决方案有两个：
 *      1). 反射动态添加字段
 *      2). 弱引用避免阻碍垃圾回收
 * 2. 校验：BuildMultiPair<KClass<T>>.by
 *      1). targetClazz是否已通过accompanyBy注册到BuildContext
 *      2). accompanyClazz与targetClazz单参构造函数参数类型是否匹配
 *      3). accompanyClazz是否非待构建类型(例如View)，是否为可build类型(例如DbModel)
 *      4). ...
 * @author liurenpeng
 * Created on 2020-06-17
 */