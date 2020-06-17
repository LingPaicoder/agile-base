package com.lpcoder.agile.base.lab.delegated.properties

import kotlin.properties.Delegates

/**
 * @author liurenpeng
 * Created on 2020-06-17
 */

class PostHierarchyVetoableDemo {
    var grade by Delegates.vetoable("T0", { _, _, _ -> true })
    var notChangeGrade by Delegates.vetoable("T0", { _, _, _ -> false })
}

fun main() {
    val ph = PostHierarchyVetoableDemo()

    ph.grade = "T1"
    ph.grade = "T2"
    ph.grade = "T3"
    println(ph.grade) // T3

    ph.notChangeGrade = "T1"
    ph.notChangeGrade = "T2"
    ph.notChangeGrade = "T3"
    println(ph.notChangeGrade) // T0
}