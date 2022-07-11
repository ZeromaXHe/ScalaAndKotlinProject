package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/9 17:04
 * @note
 * 剑指 Offer 50. 第一个只出现一次的字符 | 难度：简单 | 标签：队列、哈希表、字符串、计数
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 *
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 *
 * 限制：
 * 0 <= s 的长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer50 {
  def main(args: Array[String]): Unit = {
    println(firstUniqChar("leetcode"))
    println(firstUniqChar(""))
  }

  /**
   * 执行用时：624 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：105 / 105
   *
   * @param s
   * @return
   */
  def firstUniqChar(s: String): Char = {
    val linkedMap = new scala.collection.mutable.LinkedHashMap[Char, Int]
    s.foreach(c => linkedMap.put(c, linkedMap.getOrElse(c, 0) + 1))
    linkedMap.find(_._2 == 1).getOrElse((' ', 1))._1
  }
}
