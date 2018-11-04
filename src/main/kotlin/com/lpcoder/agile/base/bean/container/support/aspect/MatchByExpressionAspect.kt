package com.lpcoder.agile.base.bean.container.support.aspect

import com.lpcoder.agile.base.util.ClassUtil
import org.aspectj.weaver.tools.PointcutParser
import org.aspectj.weaver.tools.PointcutPrimitive

abstract class MatchByExpressionAspect(order: Int = 1,
                                             expression: String)
    : BaseAspect(order,
        { method ->
            val pointcutParser = PointcutParser
                    .getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
                            setOf(PointcutPrimitive.EXECUTION), ClassUtil.getDefaultClassLoader())
            val pointcutExpression = pointcutParser.parsePointcutExpression("execution($expression)")
            val shadowMatch = pointcutExpression.matchesMethodExecution(method)
            shadowMatch.alwaysMatches()
        })