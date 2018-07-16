package com.lpcoder.agile.base.util

import com.lpcoder.agile.base.check.CheckException
import com.lpcoder.agile.base.check.be
import com.lpcoder.agile.base.check.must
import com.lpcoder.agile.base.check.ruler.IntRuler.eq
import com.lpcoder.agile.base.check.ruler.StrRuler
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
     * 前17位受加权因子影响的和与11取模后的值与第18位值的映射
     */
    private val POWER_TO_LAST = mapOf(10 to '2', 9 to '3', 8 to '4', 7 to '5', 6 to '6', 5 to '7', 4 to '8', 3 to '9', 2 to 'x', 1 to '0', 0 to '1')

    /**
     * 验证身份证是否合法
     */
    fun validate(idCard: String?): Boolean =
            try {
                idCard must be(notNull(), notEmpty(), lengthEq(ID_CARD_LENGTH))
                val idCardFrontPart = idCard!!.substring(0, ID_CARD_LENGTH - 1)
                idCardFrontPart must be(num())
                val lastCharStr = POWER_TO_LAST[getPowerSum(convertCharArrToIntArr(idCardFrontPart.toCharArray())) % 11]
                lastCharStr!!.toInt() must eq(idCard.last().toInt())
                true
            } catch (e: CheckException) {
                false
            }

    private fun convertCharArrToIntArr(cArr: CharArray): IntArray = cArr.toList().stream().map { (it + "").toInt() }.collect(Collectors.toList()).toIntArray()

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     */
    private fun getPowerSum(iArr: IntArray): Int {
        iArr.size must eq(POWER.size)
        return iArr.indices.toList().stream().map { iArr[it] * POWER[it] }.reduce { it, i -> it + i }.get()
    }

}