package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/9/27 9:51
 * @note
 * 面试题 01.02. 判定是否互为字符重排 | 难度：简单 | 标签：哈希表、字符串、排序
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 *
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 *
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview01_02 {
  /**
   * 执行用时：456 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.8 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：23 / 23
   *
   * @param s1
   * @param s2
   * @return
   */
  def CheckPermutation(s1: String, s2: String): Boolean = {
    val map1 = s1.groupBy(identity).view.mapValues(_.length)
    val map2 = s2.groupBy(identity).view.mapValues(_.length)
    if (map1.size != map2.size) return false
    for ((k1, v1) <- map1) {
      if (!map2.contains(k1) || map2(k1) != v1) return false
    }
    true
  }
}
