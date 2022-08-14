package com.zerox.kotlin.from801to1000

/**
 * 844. 比较含退格的字符串 | 难度：简单 | 标签：栈、双指针、字符串、模拟
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 *
 * 示例 2：
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 *
 * 示例 3：
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 *
 * 提示：
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 *
 * 进阶：
 * 你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 14:29
 */
object Solution844 {
    /**
     * 执行用时：132 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：33 MB, 在所有 Kotlin 提交中击败了 60.00% 的用户
     * 通过测试用例：114 / 114
     */
    fun backspaceCompare(s: String, t: String): Boolean {
        val sReal = StringBuilder()
        val tReal = StringBuilder()
        var count = 0
        for (i in s.indices.reversed()) {
            if (s[i] == '#') count++
            else if (count > 0) count--
            else sReal.append(s[i])
        }
        count = 0
        for (i in t.indices.reversed()) {
            if (t[i] == '#') count++
            else if (count > 0) count--
            else tReal.append(t[i])
        }
        return sReal.toString() == tReal.toString()
    }
}