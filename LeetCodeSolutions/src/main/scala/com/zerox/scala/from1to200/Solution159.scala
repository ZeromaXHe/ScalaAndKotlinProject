package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/10/28 19:10
 * @note
 * 159. 至多包含两个不同字符的最长子串 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度。
 *
 * 示例 1：
 *
 * 输入：s = "eceba"
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 * 示例 2：
 *
 * 输入：s = "ccaabbb"
 * 输出：5
 * 解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
 *
 * 提示：
 * 1 <= s.length <= 105
 * s 由英文字母组成
 */
object Solution159 {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstringTwoDistinct("abaccc"))
  }

  /**
   * 时间 516 ms 击败 100%
   * 内存 53.5 MB 击败 100%
   *
   * @param s
   * @return
   */
  def lengthOfLongestSubstringTwoDistinct(s: String): Int = {
    var from1 = Int.MaxValue / 2
    var from2 = Int.MaxValue / 2
    var connect1 = Int.MaxValue / 2
    var connect2 = Int.MaxValue / 2
    var char1 = '#'
    var char2 = '#'
    var max = 1
    var pre = '#'
    for (i <- s.indices) {
      if (s(i) == char1 || s(i) == char2) {
        max = max max (i - (from1 min from2) + 1)
        if (s(i) != pre) {
          if (s(i) == char1) {
            connect1 = i
          } else {
            connect2 = i
          }
        }
      } else if (char1 == '#') {
        char1 = s(i)
        from1 = i
        connect1 = i
        max = max max (i - (from1 min from2) + 1)
      } else if (char2 == '#') {
        char2 = s(i)
        from2 = i
        connect2 = i
        max = max max (i - (from1 min from2) + 1)
      } else if (connect1 < connect2) {
        char1 = s(i)
        from1 = i
        connect1 = i
        from2 = connect2
      } else {
        char2 = s(i)
        from2 = i
        connect2 = i
        from1 = connect1
      }
      pre = s(i)
    }
    max
  }
}
