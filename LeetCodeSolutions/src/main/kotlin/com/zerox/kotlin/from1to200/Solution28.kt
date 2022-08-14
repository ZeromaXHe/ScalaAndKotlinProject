package com.zerox.kotlin.from1to200

/**
 * 28. 实现 strStr() | 难度：简单 | 标签：双指针、字符串、字符串匹配
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 * 说明：
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 *
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 16:02
 */
object Solution28 {
    /**
     * 执行用时：164 ms, 在所有 Kotlin 提交中击败了 28.92% 的用户
     * 内存消耗：34.5 MB, 在所有 Kotlin 提交中击败了 21.69% 的用户
     * 通过测试用例：75 / 75
     */
    fun strStr(haystack: String, needle: String): Int {
        return (0..haystack.length - needle.length).find { i ->
            needle.indices.all { j -> haystack[i + j] == needle[j] }
        } ?: -1
    }

    /**
     * 执行用时：136 ms, 在所有 Kotlin 提交中击败了 90.36% 的用户
     * 内存消耗：32.5 MB, 在所有 Kotlin 提交中击败了 86.75% 的用户
     * 通过测试用例：75 / 75
     */
    fun strStr_kmp(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0
        val m = needle.length
        val n = haystack.length
        val s = " $haystack"
        val p = " $needle"
        val next = IntArray(m + 1)
        var j = 0
        for (i in 2..m) {
            while (j > 0 && p[i] != p[i + 1]) j = next[j]
            if (p[i] == p[j + 1]) j++
            next[i] = j
        }
        j = 0
        for (i in 1..n) {
            while (j > 0 && s[i] != p[j + 1]) j = next[j]
            if (s[i] == p[j + 1]) j++
            if (j == m) return i - m
        }
        return -1
    }
}