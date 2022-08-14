package com.zerox.kotlin.from401to600

/**
 * 409. 最长回文串 | 难度：简单 | 标签：贪心、哈希表、字符串
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
 *
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 *
 * 示例 1:
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * 示例 2:
 * 输入:s = "a"
 * 输入:1
 *
 * 示例 3:
 * 输入:s = "bb"
 * 输入: 2
 *
 * 提示:
 * 1 <= s.length <= 2000
 * s 只能由小写和/或大写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 16:26
 */
object Solution409 {
    /**
     * 执行用时：192 ms, 在所有 Kotlin 提交中击败了 10.53% 的用户
     * 内存消耗：34.5 MB, 在所有 Kotlin 提交中击败了 5.26% 的用户
     * 通过测试用例：95 / 95
     */
    fun longestPalindrome(s: String): Int {
        val (evens, odds) = s.groupBy { it }.values.map { it.size }.partition { it % 2 == 0 }
        return evens.sum() + odds.sum() - odds.size + if (odds.isEmpty()) 0 else 1
    }
}