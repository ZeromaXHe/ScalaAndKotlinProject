package com.zerox.kotlin.from1to200

/**
 * 66. 加一 | 难度：简单 | 标签：数组、数学
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 10:20
 */
object Solution66 {
    /**
     * 执行用时：176 ms, 在所有 Kotlin 提交中击败了 48.75% 的用户
     * 内存消耗：33.6 MB, 在所有 Kotlin 提交中击败了 83.75% 的用户
     * 通过测试用例：111 / 111
     *
     * 前面检查全部为 9 的逻辑可以改为最后检查 carry 是不是还是 1
     */
    fun plusOne(digits: IntArray): IntArray {
        if (digits.all { it == 9 }) {
            val result = IntArray(digits.size + 1)
            result[0] = 1
            return result
        }
        var i = digits.size - 1
        var carry = 1
        while (carry > 0 && i >= 0) {
            if (digits[i] == 9) {
                digits[i] = 0
            } else {
                digits[i]++
                carry = 0
            }
            i -= 1
        }
        return digits
    }
}