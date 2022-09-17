package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/9/17 9:39
 * @note
 * 1624. 两个相同字符之间的最长子字符串 | 难度：简单 | 标签：哈希表、字符串
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 *
 * 示例 3：
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 *
 * 示例 4：
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *
 * 提示：
 * 1 <= s.length <= 300
 * s 只含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-substring-between-two-equal-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1624 {
  /**
   * 执行用时：452 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：54 / 54
   *
   * @param s
   * @return
   */
  def maxLengthBetweenEqualCharacters(s: String): Int = {
    var max = -1
    val firstAppearance = Array.fill(26)(s.length)
    for (i <- s.indices) {
      val idx = s(i) - 'a'
      if (firstAppearance(idx) == s.length) firstAppearance(idx) = i
      else if (i - firstAppearance(idx) - 1 > max) max = i - firstAppearance(idx) - 1
    }
    max
  }
}
