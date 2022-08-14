package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 13:37
 * @note
 * 1416. 恢复数组 | 难度：困难 | 标签：字符串、动态规划
 * 某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。
 *
 * 给你字符串 s 和整数 k 。可能会有多种不同的数组恢复结果。
 *
 * 按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。
 *
 * 由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
 *
 * 示例 1：
 * 输入：s = "1000", k = 10000
 * 输出：1
 * 解释：唯一一种可能的数组方案是 [1000]
 *
 * 示例 2：
 * 输入：s = "1000", k = 10
 * 输出：0
 * 解释：不存在任何数组方案满足所有整数都 >= 1 且 <= 10 同时输出结果为 s 。
 *
 * 示例 3：
 * 输入：s = "1317", k = 2000
 * 输出：8
 * 解释：可行的数组方案为 [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7]
 *
 * 示例 4：
 * 输入：s = "2020", k = 30
 * 输出：1
 * 解释：唯一可能的数组方案是 [20,20] 。 [2020] 不是可行的数组方案，原因是 2020 > 30 。 [2,020] 也不是可行的数组方案，因为 020 含有前导 0 。
 *
 * 示例 5：
 * 输入：s = "1234567890", k = 90
 * 输出：34
 *
 * 提示：
 * 1 <= s.length <= 10^5.
 * s 只包含数字且不包含前导 0 。
 * 1 <= k <= 10^9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/restore-the-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1416 {
  def main(args: Array[String]): Unit = {
    println(numberOfArrays("1000", 10000)) // 1
    println(numberOfArrays("1111111111111", 1000000000)) // 4076
    println(numberOfArrays("600342244431311113256628376226052681059918526204", 703)) // 411743991
  }

  /**
   * 执行用时：716 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：83 / 83
   *
   * @param s
   * @param k
   * @return
   */
  def numberOfArrays(s: String, k: Int): Int = {
    val MOD = (1e9 + 7).toInt
    var vk = k / 10
    var kLen = 1
    var kMask = 1L
    while (vk > 0) {
      vk /= 10
      kLen += 1
      kMask *= 10
    }
    val dp = Array.fill(kLen)(1)
    for (i <- s.indices) {
      var newDp = 0
      var initMask = 1L
      for (j <- 0 until kLen) {
        if (i - j >= 0 && s(i - j) != '0') {
          var cmp = 0
          if (k >= initMask * 10) cmp = -1
          else {
            vk = k
            var idx = j
            var mask = initMask
            while (idx >= 0 && cmp == 0) {
              if (s(i - idx) - '0' < (vk / mask) % 10) cmp = -1
              else if (s(i - idx) - '0' > (vk / mask) % 10) cmp = 1
              mask /= 10
              idx -= 1
            }
          }
          if (cmp <= 0) newDp = (dp((i - j - 1 + kLen) % kLen) + newDp) % MOD
        }
        initMask *= 10
      }
      dp(i % kLen) = newDp
    }
    dp((s.length - 1) % kLen)
  }
}
