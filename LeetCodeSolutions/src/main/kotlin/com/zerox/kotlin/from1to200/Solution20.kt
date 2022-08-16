package com.zerox.kotlin.from1to200

/**
 * 20. 有效的括号 | 难度：简单 | 标签：栈、字符串
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 *
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 15:14
 */
object Solution20 {
    /**
     * 执行用时：128 ms, 在所有 Kotlin 提交中击败了 99.46% 的用户
     * 内存消耗：32.7 MB, 在所有 Kotlin 提交中击败了 82.80% 的用户
     * 通过测试用例：92 / 92
     */
    fun isValid(s: String): Boolean {
        val stack = java.util.LinkedList<Char>()
        for (c in s) {
            when (c) {
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                else -> if (stack.isEmpty() || stack.pop() != c) return false
            }
        }
        return stack.isEmpty()
    }
}