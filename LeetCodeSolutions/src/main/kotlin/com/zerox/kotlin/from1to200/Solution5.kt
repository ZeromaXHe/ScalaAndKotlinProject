package com.zerox.kotlin.from1to200


/**
 * 5. 最长回文子串 | 难度：中等 | 标签：字符串、动态规划
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/17 14:19
 */
object Solution5 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(longestPalindrome("cbbd"))
    }

    /**
     * 执行用时：212 ms, 在所有 Kotlin 提交中击败了 82.91% 的用户
     * 内存消耗：34.7 MB, 在所有 Kotlin 提交中击败了 88.61% 的用户
     * 通过测试用例：140 / 140
     */
    fun longestPalindrome(s: String): String {
        if (s.length == 1) return s
        var start = 0
        var end = 0
        for (i in s.indices) {
            val len = expandAroundCenter(s, i, i).coerceAtLeast(expandAroundCenter(s, i, i + 1))
            if (len > end + 1 - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }
        return s.substring(start, end + 1)
    }

    private fun expandAroundCenter(s: String, l: Int, r: Int): Int {
        var vl = l
        var vr = r
        while (vl >= 0 && vr < s.length && s[vl] == s[vr]) {
            vl--
            vr++
        }
        return vr - vl - 1
    }
}