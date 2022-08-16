package com.zerox.kotlin.from1to200

/**
 * 32. 最长有效括号 | 难度：困难 | 标签：栈、字符串、动态规划
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 18:25
 */
object Solution32 {
    /**
     * 执行用时：188 ms, 在所有 Kotlin 提交中击败了 57.41% 的用户
     * 内存消耗：34.5 MB, 在所有 Kotlin 提交中击败了 81.48% 的用户
     * 通过测试用例：231 / 231
     *
     * 参考题解做的
     */
    fun longestValidParentheses(s: String): Int {
        var result = 0
        val dp = IntArray(s.length)
        for (i in 1 until s.length) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = (if (i >= 2) dp[i - 2] else 0) + 2
                } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + (if ((i - dp[i - 1]) >= 2) dp[i - dp[i - 1] - 2] else 0) + 2
                }
                result = result.coerceAtLeast(dp[i])
            }
        }
        return result
    }

    /**
     * 执行用时：204 ms, 在所有 Kotlin 提交中击败了 37.04% 的用户
     * 内存消耗：34.9 MB, 在所有 Kotlin 提交中击败了 61.11% 的用户
     * 通过测试用例：231 / 231
     *
     * 参考题解做的
     */
    fun longestValidParentheses_stack(s: String): Int {
        var result = 0
        val stack = java.util.LinkedList<Int>()
        stack.push(-1)
        for (i in s.indices) {
            if (s[i] == '(') {
                stack.push(i)
            } else {
                stack.pop()
                if (stack.isEmpty()) stack.push(i)
                else result = result.coerceAtLeast(i - stack.peek())
            }
        }
        return result
    }

    /**
     * 执行用时：224 ms, 在所有 Kotlin 提交中击败了 31.48% 的用户
     * 内存消耗：35.3 MB, 在所有 Kotlin 提交中击败了 31.48% 的用户
     * 通过测试用例：231 / 231
     *
     * 参考题解做的
     */
    fun longestValidParentheses_greedy(s: String): Int {
        var left = 0
        var right = 0
        var result = 0
        for (i in 0 until s.length) {
            if (s[i] == '(') left++
            else right++

            if (left == right) result = result.coerceAtLeast(2 * right)
            else if (right > left) {
                left = 0
                right = 0
            }
        }
        left = 0
        right = 0
        for (i in s.length - 1 downTo 0) {
            if (s[i] == '(') left++
            else right++

            if (left == right) result = result.coerceAtLeast(2 * left)
            else if (left > right) {
                left = 0
                right = 0
            }
        }
        return result
    }
}