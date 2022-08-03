package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/8/3 18:28
 * @note
 * 10. 正则表达式匹配 | 难度：困难 | 标签：递归、字符串、动态规划
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 提示：
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution10 {
  def main(args: Array[String]): Unit = {
    println(isMatch("aa", "a*")) // true
    println(isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*")) // true
  }

  /**
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 60.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 80.00% 的用户
   * 通过测试用例：353 / 353
   *
   * @param s
   * @param p
   * @return
   */
  def isMatch(s: String, p: String): Boolean = {
    val dp = Array.ofDim[Int](s.length, p.length)
    isMatch(dp, s, 0, p, 0)
  }

  def isMatch(dp: Array[Array[Int]], s: String, si: Int, p: String, pi: Int): Boolean = {
    if (pi == p.length && si == s.length) return true
    else if (pi == p.length) return false
    else if (si == s.length) {
      if ((p.length - pi) % 2 == 1) return false
      var i = pi
      while (i < p.length) {
        if (p(i + 1) != '*') return false
        i += 2
      }
      return true
    }

    if (dp(si)(pi) != 0) return dp(si)(pi) == 1

    var result: Boolean = true
    if (pi + 1 < p.length && p(pi + 1) == '*') {
      if (p(pi) == '.') {
        result = isMatch(dp, s, si + 1, p, pi) || isMatch(dp, s, si, p, pi + 2)
      } else {
        result = isMatch(dp, s, si, p, pi + 2) || (s(si) == p(pi) && isMatch(dp, s, si + 1, p, pi))
      }
    } else {
      result = (p(pi) == '.' || s(si) == p(pi)) && isMatch(dp, s, si + 1, p, pi + 1)
    }
    dp(si)(pi) = if (result) 1 else -1
    result
  }
}
