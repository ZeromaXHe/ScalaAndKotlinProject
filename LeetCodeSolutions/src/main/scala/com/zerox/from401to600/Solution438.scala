package com.zerox.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/8/13 12:25
 * @note
 * 438. 找到字符串中所有字母异位词 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution438 {
  /**
   * 执行用时：624 ms, 在所有 Scala 提交中击败了 80.00% 的用户
   * 内存消耗：55.3 MB, 在所有 Scala 提交中击败了 40.00% 的用户
   * 通过测试用例：61 / 61
   *
   * @param s
   * @param p
   * @return
   */
  def findAnagrams(s: String, p: String): List[Int] = {
    if (p.length > s.length) return List[Int]()
    val map = new scala.collection.mutable.HashMap[Char, Int]
    for (i <- p.indices) {
      val count1 = map.getOrElse(s(i), 0) + 1
      if (count1 == 0) map.remove(s(i))
      else map(s(i)) = count1
      val count2 = map.getOrElse(p(i), 0) - 1
      if (count2 == 0) map.remove(p(i))
      else map(p(i)) = count2
    }
    val list = new scala.collection.mutable.ListBuffer[Int]
    if (map.isEmpty) list += 0
    for (i <- 0 until s.length - p.length) {
      val count1 = map.getOrElse(s(i + p.length), 0) + 1
      if (count1 == 0) map.remove(s(i + p.length))
      else map(s(i + p.length)) = count1
      val count2 = map.getOrElse(s(i), 0) - 1
      if (count2 == 0) map.remove(s(i))
      else map(s(i)) = count2
      if (map.isEmpty) list += i + 1
    }
    list.toList
  }
}
