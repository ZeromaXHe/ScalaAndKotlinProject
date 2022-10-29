package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 17:58
 * @note
 * 249. 移位字符串分组 | 难度：中等 | 标签：数组、哈希表、字符串
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
 *
 * 示例：
 * 输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 * 输出：
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 * 解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-shifted-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution249 {
  /**
   * 执行用时：516 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：24 / 24
   *
   * @param strings
   * @return
   */
  def groupStrings(strings: Array[String]): List[List[String]] = {
    strings.groupBy(s => {
      val sb = new StringBuilder
      for (i <- 0 to s.length - 2) {
        var diff = (s(i + 1) - 'a') - (s(i) - 'a')
        if (diff < 0) diff += 26
        sb.append(diff).append(":")
      }
      sb.toString()
    }).view.mapValues(_.toList).values.toList
  }
}
