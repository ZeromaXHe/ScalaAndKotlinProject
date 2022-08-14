package com.zerox.kotlin.from1to200

/**
 * 43. 字符串相乘 | 难度：中等 | 标签：数字、字符串、模拟
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 11:22
 */
object Solution43 {
    /**
     * 执行用时：180 ms, 在所有 Kotlin 提交中击败了 81.40% 的用户
     * 内存消耗：36.1 MB, 在所有 Kotlin 提交中击败了 53.49% 的用户
     * 通过测试用例：311 / 311
     */
    fun multiply(num1: String, num2: String): String {
        if ("0".equals(num1) || "0".equals(num2)) return "0"
        val result = IntArray(num1.length + num2.length)
        for (i in num1.indices) {
            for (j in num2.indices) {
                val prod = (num1[i] - '0') * (num2[j] - '0')
                add(result, i + j + 1, prod % 10)
                if (prod >= 10) add(result, i + j, prod / 10)
            }
        }
        val sb = StringBuilder()
        for (i in result.indices) {
            if (i != 0 || result[i] != 0) sb.append(result[i])
        }
        return sb.toString()
    }

    private fun add(result: IntArray, idx: Int, add: Int) {
        var i = idx
        result[i] += add
        while (result[i] >= 10) {
            result[i - 1] += result[i] / 10
            result[i] %= 10
            i--
        }
    }
}