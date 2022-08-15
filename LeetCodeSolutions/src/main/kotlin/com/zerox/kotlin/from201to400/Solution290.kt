package com.zerox.kotlin.from201to400

/**
 * 290. 单词规律 | 难度：简单 | 标签：哈希表、字符串
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 *
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 *
 * 提示:
 * 1 <= pattern.length <= 300
 * pattern 只包含小写英文字母
 * 1 <= s.length <= 3000
 * s 只包含小写英文字母和 ' '
 * s 不包含 任何前导或尾随对空格
 * s 中每个单词都被 单个空格 分隔
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 14:46
 */
object Solution290 {
    /**
     * 执行用时：152 ms, 在所有 Kotlin 提交中击败了 77.78% 的用户
     * 内存消耗：34.9 MB, 在所有 Kotlin 提交中击败了 66.67% 的用户
     * 通过测试用例：36 / 36
     */
    fun wordPattern(pattern: String, s: String): Boolean {
        val split = s.split(" ")
        if (split.size != pattern.length) return false
        val pToStrMap = mutableMapOf<Char, String>()
        val strToPMap = mutableMapOf<String, Char>()
        for (i in pattern.indices) {
            if (pToStrMap.containsKey(pattern[i])) {
                if (split[i] != pToStrMap[pattern[i]]) return false
            } else if (strToPMap.containsKey(split[i])) {
                return false
            } else {
                pToStrMap[pattern[i]] = split[i]
                strToPMap[split[i]] = pattern[i]
            }
        }
        return true
    }
}