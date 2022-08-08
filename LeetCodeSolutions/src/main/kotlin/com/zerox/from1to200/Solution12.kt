package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/8/8 18:31
 * 12. 整数转罗马数字 | 难度：中等 | 标签：哈希表、数字、字符串
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 *
 * 示例 1:
 * 输入: num = 3
 * 输出: "III"
 *
 * 示例 2:
 * 输入: num = 4
 * 输出: "IV"
 *
 * 示例 3:
 * 输入: num = 9
 * 输出: "IX"
 *
 * 示例 4:
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 示例 5:
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 提示：
 * 1 <= num <= 3999
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution12 {
    /**
     * 执行用时：180 ms, 在所有 Kotlin 提交中击败了 91.38% 的用户
     * 内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了 74.14% 的用户
     * 通过测试用例：3999 / 3999
     */
    fun intToRoman(num: Int): String {
        val sb: StringBuilder = StringBuilder()
        if (num >= 1000) {
            var count = num / 1000
            while (count > 0) {
                sb.append('M')
                count--
            }
        }
        if (num >= 100) {
            sb.appendDigitRoman(num / 100 % 10, 'C', 'D', 'M')
        }
        if (num >= 10) {
            sb.appendDigitRoman(num / 10 % 10, 'X', 'L', 'C')
        }
        sb.appendDigitRoman(num % 10, 'I', 'V', 'X')
        return sb.toString()
    }

    private fun StringBuilder.appendDigitRoman(digit: Int, one: Char, five: Char, ten: Char) {
        when {
            digit == 4 -> this.append(one).append(five)
            digit == 9 -> this.append(one).append(ten)
            digit >= 5 -> {
                this.append(five)
                var count = digit - 5
                while (count > 0) {
                    this.append(one)
                    count--
                }
            }
            else -> {
                var count = digit
                while (count > 0) {
                    this.append(one)
                    count--
                }
            }
        }
    }
}