package com.zerox

/**
 * @author ZeromaXHe
 * @since 2022/7/3 8:41
 * @note
 * 556. 下一个更大元素 III | 难度：中等 | 标签：数学、双指针、字符串
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 *
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 *
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 *
 * 提示：
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/next-greater-element-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution556 {
  def main(args: Array[String]): Unit = {
    println(nextGreaterElement(217469654) == 217494566)
    println(nextGreaterElement(2147483476) == 2147483647)
    println(nextGreaterElement(1999999999) == -1)
  }

  /**
   * 执行用时：416 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：39 / 39
   *
   * @param n
   * @return
   */
  def nextGreaterElement(n: Int): Int = {
    var div = 10
    var divPre = 1
    while (n / div > 0) {
      val digit = n / div % 10
      val digitPre = n / divPre % 10
      if (digit < digitPre) {
        var multi = 1
        while (n / multi % 10 <= digit) {
          multi *= 10
        }
        var result = n.toLong + digit * (multi - div.toLong) + n / multi % 10 * (div.toLong - multi)
        val sort = (result % div).toString.sorted.toLong
        result += sort - result % div
        if (result > Int.MaxValue) return -1 else return result.toInt
      } else {
        divPre = div
        div *= 10
      }
    }
    -1
  }
}
