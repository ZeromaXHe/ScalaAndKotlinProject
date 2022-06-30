package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 17:00
 * @note
 * 面试题 16.18. 模式匹配 | 难度：中等 | 标签：数学、字符串、回溯、枚举
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 *
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 *
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 *
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 *
 * 提示：
 * 1 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pattern-matching-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_18 {
  def main(args: Array[String]): Unit = {
    println(patternMatching("abba", "dogcatcatdog"))
    println(patternMatching("aaaa", "dogcatcatdog"))
    println(patternMatching("abbaa", "dogdogdogdogdog"))
    println(patternMatching("bbb", "xxxxxx"))
  }

  /**
   * 执行用时：448 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：92 / 92
   *
   * @param pattern
   * @param value
   * @return
   */
  def patternMatching(pattern: String, value: String): Boolean = {
    if (pattern.isEmpty) return value.isEmpty
    val countA = pattern.count(_ == 'a')
    val countB = pattern.length - countA
    if (countB == 0 || countA == 0) {
      if (value.length % pattern.length != 0) return false
      var count = 1
      var test = true
      val len = value.length / pattern.length
      val str = value.substring(0, len)
      while (test && count < pattern.length) {
        if (value.indexOf(str, count * len) != count * len) test = false
        count += 1
      }
      return test
    }
    var lenA = 0
    var result = false
    while (!result && lenA <= value.length / countA) {
      val totalLenB = value.length - countA * lenA
      if (totalLenB % countB == 0) {
        val lenB = totalLenB / countB
        var patternIndex = 0
        var test = true
        var valueIndex = 0
        var strA: String = null
        var strB: String = null
        while (test && patternIndex < pattern.length) {
          if (pattern(patternIndex) == 'a') {
            if (strA == null) {
              strA = value.substring(valueIndex, valueIndex + lenA)
              if (strA == strB) test = false
            }
            else if (value.indexOf(strA, valueIndex) != valueIndex) test = false
            valueIndex += lenA
          } else {
            if (strB == null) {
              strB = value.substring(valueIndex, valueIndex + lenB)
              if (strA == strB) test = false
            }
            else if (value.indexOf(strB, valueIndex) != valueIndex) test = false
            valueIndex += lenB
          }
          patternIndex += 1
        }
        if (test) result = true
      }
      lenA += 1
    }
    result
  }
}
