package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/8/3 16:22
 * @note
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
 */
object Solution5 {
  /**
   * 执行用时：528 ms, 在所有 Scala 提交中击败了 85.71% 的用户
   * 内存消耗：52.9 MB, 在所有 Scala 提交中击败了 78.57% 的用户
   * 通过测试用例：140 / 140
   *
   * @param s
   * @return
   */
  def longestPalindrome(s: String): String = {
    var max = 1
    var maxl = -1
    var maxr = 1
    for (i <- s.indices) {
      var l = i - 1
      var r = i + 1
      while (l >= 0 && r < s.length && s(l) == s(r)) {
        l -= 1
        r += 1
      }
      if (r - l - 1 > max) {
        max = r - l - 1
        maxl = l
        maxr = r
      }
      if (i > 0) {
        l = i - 1
        r = i
        while (l >= 0 && r < s.length && s(l) == s(r)) {
          l -= 1
          r += 1
        }
        if (r - l - 1 > max) {
          max = r - l - 1
          maxl = l
          maxr = r
        }
      }
    }
    s.substring(maxl + 1, maxr)
  }
}
