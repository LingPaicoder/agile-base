package com.lpcoder.agile.base.util

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.IntRuler.eq
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthEq
import com.lpcoder.agile.base.check.ruler.StrRuler.notEmpty
import com.lpcoder.agile.base.check.ruler.StrRuler.notNull
import com.lpcoder.agile.base.check.ruler.StrRuler.num
import java.util.stream.Collectors

/**
 * 身份证号工具类(只适用于18位大陆身份证号)
 * @author: liurenpeng
 * @date: Created in 18-7-13
 */
object IdCardUtil {

    /**
     * 中国公民身份证号码长度。
     */
    private val ID_CARD_LENGTH = 18

    /**
     * 每位加权因子
     */
    private val POWER = intArrayOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2)

    /**
     * 受加权因子影响后
     */

    /**
     * 最低年限
     */
    private val MIN_YEAR = 1930

    /**
     * 验证身份证是否合法
     */
    fun validateIdCard(idCard: String?): Boolean {
        try {
            idCard must be(notNull(), notEmpty(), lengthEq(ID_CARD_LENGTH))
            val idCardFrontPart = idCard?.substring(0, ID_CARD_LENGTH - 1)
            idCardFrontPart must be(num())

        } catch (e: CheckException) {

        }
        return false
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     */
    private fun getPowerSum(iArr: Array<Int>): Int {
        iArr.size must eq(POWER.size)
        return iArr.indices.toList().stream()
                .map { iArr[it] * POWER[it] }
                .reduce { it, i -> it + i }.get()
    }

}