package com.lpcoder.agile.base.model.builder

/**
 * 1. ModelBuilderDelegate有内存泄露风险，解决方案有两个：
 *      1). 反射动态添加字段
 *      2). 弱引用避免阻碍垃圾回收
 * 2. 校验：BuildMultiPair<KClass<T>>.by
 *      1). targetClazz是否已通过accompanyBy注册到BuildContext
 *      2). accompanyClazz与targetClazz单参构造函数参数类型是否匹配
 *      3). accompanyClazz是否非待构建类型(例如View)，是否为可build类型(例如DbModel)
 *      4). "outJoin SHARED"，outJoinPoint只能有一个
 *      5). ...
 * 3. CacheAccessor,未查到记录也可以记录下来，避免缓存击穿
 * 4. 代理属性不支持set合理么
 * 5. ModelBuilder::accompanyMap有必要存在么，或者说，有必要是map么
 * 6. error("")，把错误信息描述一下
 * 7. 循环依赖怎么办？判断是否易注值，注值则停止？或者提前检查禁止循环依赖？
 * 8. delegate.Join.buildTarget时，构建出来的targets是cache了的么？有办法cache么
 * @author liurenpeng
 * Created on 2020-06-17
 */