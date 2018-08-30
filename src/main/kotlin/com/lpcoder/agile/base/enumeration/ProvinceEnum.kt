package com.lpcoder.agile.base.enumeration

enum class ProvinceEnum(val code: Int, val desc: String) {
    BEI_JING(11, "北京"),
    TIAN_JIN(12, "天津"),
    HE_BEI(13, "河北"),
    SHAN_XI_ONE_TONE(14, "山西"),
    NEI_MENG_GU(15, "内蒙古"),
    LIAO_NING(21, "辽宁"),
    JI_LIN(22, "吉林"),
    HEI_LONG_JIANG(23, "黑龙江"),
    SHANG_HAI(31, "上海"),
    JIANG_SU(32, "江苏"),
    ZHE_JIANG(33, "浙江"),
    AN_HUI(34, "安徽"),
    FU_JIAN(35, "福建"),
    JIANG_XI(36, "江西"),
    SHAN_DONG(37, "山东"),
    HE_NAN(41, "河南"),
    KU_BEI(42, "湖北"),
    HU_NAN(43, "湖南"),
    GUANG_DONG(44, "广东"),
    GUANG_XI(45, "广西"),
    HAI_NAN(46, "海南"),
    CHONG_QING(50, "重庆"),
    SI_CHUAN(51, "四川"),
    GUI_ZHOU(52, "贵州"),
    YUN_NAN(53, "云南"),
    XI_ZANG(54, "西藏"),
    SHAN_XI_THREE_TONE(61, "陕西"),
    GAN_SU(62, "甘肃"),
    QING_HAI(63, "青海"),
    NING_XIA(64, "宁夏"),
    XIN_JIANG(65, "新疆"),
    TAI_WAN(71, "台湾"),
    XIANG_GANG(81, "香港"),
    AO_MEN(82, "澳门"),
    GUO_WAI(91, "国外");

    companion object {
        fun getByCode(code: Int): ProvinceEnum {
            return enumValues<ProvinceEnum>().first { provinceEnum -> code == provinceEnum.code }
        }
    }
}