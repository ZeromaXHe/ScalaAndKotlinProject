package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/8/16 16:38
 * @note
 * 459. 重复的子字符串 | 难度：简单 | 标签：字符串、字符串匹配
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 *
 * 示例 1:
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: s = "aba"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution459 {
  def repeatedSubstringPattern(s: String): Boolean = {
    var split = 2
    while (split <= s.length) {
      if (s.length % split == 0) {
        val len = s.length / split
        var i = len
        var matched = true
        while (matched && i < s.length) {
          if (s(i) != s(i % len)) matched = false
          i += 1
        }
        if (matched) return true
      }
      split += 1
    }
    false
  }
}
