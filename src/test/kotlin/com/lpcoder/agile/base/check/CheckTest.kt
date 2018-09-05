package com.lpcoder.agile.base.check

import com.lpcoder.agile.base.check.ruler.AnyRuler
import com.lpcoder.agile.base.check.ruler.IntRuler
import com.lpcoder.agile.base.check.ruler.IntRuler.lte
import com.lpcoder.agile.base.check.ruler.StrRuler.beEmpty
import com.lpcoder.agile.base.check.ruler.StrRuler.beIdCard
import com.lpcoder.agile.base.check.ruler.StrRuler.beNotEmpty
import com.lpcoder.agile.base.check.ruler.StrRuler.beNullVal
import com.lpcoder.agile.base.check.ruler.StrRuler.idCard
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthGte
import com.lpcoder.agile.base.check.ruler.StrRuler.lengthLte
import com.lpcoder.agile.base.check.ruler.StrRuler.notNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class CheckTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    /**
     * 通过alias、must中缀方法以类自然语言方式编程做校验(自解释性强,推荐)
     */
    @Test
    fun mustTest() {
        val trueIdCard = "130802198108204219"
        trueIdCard alias "身份证号" must beIdCard

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-11007, desc=身份证号必须符合身份证格式")
        val falseIdCard = "130802198108204210"
        falseIdCard alias "身份证号" must beIdCard
    }

    /**
     * 通过doCheck方法以传统Java语法方式做校验(自解释性弱,不推荐)
     */
    @Test
    fun doCheckTest() {
        val trueIdCard = "130802198108204219"
        doCheck(trueIdCard, "身份证号", beIdCard)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-11007, desc=身份证号必须符合身份证格式")
        val falseIdCard = "130802198108204210"
        doCheck(falseIdCard, "身份证号", beIdCard)
    }

    /**
     * 若校验失败则会抛出CheckException异常
     */
    @Test
    fun exceptionTest() {
        try {
            val falseIdCard = "130802198108204210"
            falseIdCard alias "身份证号" must beIdCard
        } catch (e: CheckException) {
            Assert.assertEquals(-11007L, e.code)
            Assert.assertEquals("身份证号必须符合身份证格式", e.desc)
            Assert.assertEquals("code=-11007, desc=身份证号必须符合身份证格式", e.message)
        }
    }

    /**
     * 通过ruler.or方法实现规则的“或”逻辑
     */
    @Test
    fun orTest() {
        val beName = beEmpty.or(lengthGte(2) and lengthLte(10))

        var specialName = ""
        specialName alias "姓名" must beName

        specialName = "张三"
        specialName alias "姓名" must beName

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-11005, desc=姓名的长度必须小于或等于10")
        specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫"
        specialName alias "姓名" must beName
    }

    /**
     * 除了beNullVal和nullVal()之外的其他内置Ruler,
     * 都会先进行beNotNull校验,若业务场景允许为null,可用或逻辑处理
     */
    @Test
    fun nullTest() {
        val idCard: String? = null
        idCard alias "身份证号" must beNullVal.or(beIdCard)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-11013, desc=身份证号不能为Null")
        idCard alias "身份证号" must beIdCard
    }

    /**
     * 可为校验对象设置别名,如果不需要别名,也可不传,别名默认值为空串""
     */
    @Test
    fun aliasTest() {
        val idCard: String? = null
        // 不使用别名
        idCard must beNullVal.or(beIdCard)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-11013, desc=身份证号不能为Null")
        // 使用别名
        idCard alias "身份证号" must beIdCard
    }

    /**
     * 自定义错误编号和错误描述(支持在desc中对norm进行格式化)
     */
    @Test
    fun userDefinedFailCodeAndDescTest() {
        val specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫"
        val nameDesc = "姓名"
        val nameTooLongCode = -2L
        val lteNorm = 10
        // 注意此处%d的用法
        val nameTooLongDesc = "长度超过限制,允许的最大长度:%d"

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-2, desc=姓名长度超过限制,允许的最大长度:10")

        specialName alias nameDesc must be(lengthGte(2),
                lengthLte(lteNorm, nameTooLongCode, nameTooLongDesc))
    }

    data class Custom(var customId: String?, var name: String?, var age: Int?)

    /**
     * 为实体类不同操作封装个性化规则/自定义规则
     */
    @Test
    fun testEntity() {
        val customAddRuler = Ruler { custom: Custom? ->
            doCheck(custom, "商家", AnyRuler.beNotNull)
            custom?.customId alias "商家Id" must beNotEmpty
            custom?.name alias "商家姓名" must beNotEmpty
            custom?.age alias "商家年龄" must lte(60)
        }

        val custom = Custom("123", "张三", 80)

        thrown.expect(CheckException::class.java)
        thrown.expectMessage("code=-18005, desc=商家年龄必须小于或等于60")
        custom must be(customAddRuler)
    }
}
