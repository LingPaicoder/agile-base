package com.lpcoder.agile.base.check

/**
 * 规则类
 * @author: liurenpeng
 * @date: Created in 18-7-11
 */

class Ruler<T>(val verifier: (T) -> Unit) {

    /**
     * 或操作
     */
    fun or(vararg rulers: Ruler<T>): Ruler<T> {
        return Ruler { checkTarget: T ->
            try {
                this.verifier(checkTarget)
            } catch (e: CheckException) {
                rulers.forEach { it.verifier(checkTarget) }
            }

        }
    }

    companion object {
        /**
         * noneNorm-Ruler
         */
        fun <T> of(failCode: Long, failDesc: String, primaryVerifier: (T) -> Boolean): Ruler<T> {
            return Ruler { checkTarget: T ->
                if (null != checkTarget && !primaryVerifier(checkTarget)) {
                    throw CheckException(failCode, failDesc)
                }
            }
        }

        /**
         * oneNorm-Ruler
         */
        fun <T, N> of(norm: N, failCode: Long, failDesc: String, primary: (T, N) -> Boolean): Ruler<T> {
            return Ruler { checkTarget: T ->
                if (null != checkTarget && !primary(checkTarget, norm)) {
                    throw CheckException(failCode, String.format(failDesc, norm))
                }
            }
        }

        /**
         * notNull-Ruler
         */
        fun <T> ofNotNull(failCode: Long, failDesc: String): Ruler<T> = Ruler { checkTarget: T ->
            if (null == checkTarget) {
                throw CheckException(failCode, failDesc)
            }
        }

        /**
         * Ruler整合
         */
        @SafeVarargs
        fun <T> ofAll(vararg rulers: Ruler<T>): Ruler<T> {
            return Ruler { checkTarget: T -> rulers.forEach { it.verifier(checkTarget) } }
        }

    }

}

