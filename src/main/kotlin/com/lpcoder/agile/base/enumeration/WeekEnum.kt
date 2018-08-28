package com.lpcoder.agile.base.enumeration

enum class WeekEnum(val code: Int, val desc: String) {
    SUNDAY(1, "周日"),
    MONDAY(2, "周一"),
    TUESDAY(3, "周二"),
    WEDNESDAY(4, "周三"),
    THURSDAY(5, "周四"),
    FRIDAY(6, "周五"),
    SATURDAY(7, "周六");

    companion object {
        fun getByCode(code: Int): WeekEnum {
            return enumValues<WeekEnum>().first { weekEnum -> code == weekEnum.code }
        }
    }
}
