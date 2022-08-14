package com.zerox.kotlin.from401to600

/**
 * 415. 字符串相加 | 难度：简单 | 标签：数学、字符串、模拟
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 16:19
 */
object Solution415 {
    /**
     * 执行用时：164 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：34.7 MB, 在所有 Kotlin 提交中击败了 51.61% 的用户
     * 通过测试用例：317 / 317
     */
    fun addStrings(num1: String, num2: String): String {
        val sb = StringBuilder()
        var p1 = num1.length - 1
        var p2 = num2.length - 1
        var carry = 0
        while (p1 >= 0 && p2 >= 0) {
            val sum = carry + (num1[p1] - '0') + (num2[p2] - '0')
            sb.append(sum % 10)
            carry = sum / 10
            p1--
            p2--
        }
        while (p1 >= 0) {
            val sum = carry + (num1[p1] - '0')
            sb.append(sum % 10)
            carry = sum / 10
            p1--
        }
        while (p2 >= 0) {
            val sum = carry + (num2[p2] - '0')
            sb.append(sum % 10)
            carry = sum / 10
            p2--
        }
        if (carry > 0) sb.append(carry)
        return sb.reverse().toString()
    }
}