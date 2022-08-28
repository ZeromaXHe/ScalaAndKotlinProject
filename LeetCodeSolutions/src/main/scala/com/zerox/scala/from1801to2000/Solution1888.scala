package com.zerox.scala.from1801to2000

/**
 * @author ZeromaXHe
 * @since 2022/8/28 10:10
 * @note
 * 1888. 使二进制字符串字符交替的最少反转次数 | 难度：中等 | 标签：贪心、字符串、动态规划、滑动窗口
 * 给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：
 *
 * 类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
 * 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
 * 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。
 *
 * 我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。
 *
 * 比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
 *
 * 示例 1：
 * 输入：s = "111000"
 * 输出：2
 * 解释：执行第一种操作两次，得到 s = "100011" 。
 * 然后对第三个和第六个字符执行第二种操作，得到 s = "101010" 。
 *
 * 示例 2：
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替的。
 *
 * 示例 3：
 * 输入：s = "1110"
 * 输出：1
 * 解释：对第二个字符执行第二种操作，得到 s = "1010" 。
 *
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 要么是 '0' ，要么是 '1' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1888 {
  /**
   * 执行用时：680 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：62.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：65 / 65
   *
   * @param s
   * @return
   */
  def minFlips(s: String): Int = {
    val n = s.length
    val pre = Array.ofDim[Int](n, 2)
    for (i <- s.indices) {
      pre(i)(0) = (if (i == 0) 0 else pre(i - 1)(1)) + (if (s(i) == '1') 1 else 0)
      pre(i)(1) = (if (i == 0) 0 else pre(i - 1)(0)) + (if (s(i) == '0') 1 else 0)
    }
    var res = math.min(pre(n - 1)(0), pre(n - 1)(1))
    if (n % 2 == 0) return res
    val suf = Array.ofDim[Int](n, 2)
    for (i <- s.indices.reverse) {
      suf(i)(0) = (if (i == n - 1) 0 else suf(i + 1)(1)) + (if (s(i) == '1') 1 else 0)
      suf(i)(1) = (if (i == n - 1) 0 else suf(i + 1)(0)) + (if (s(i) == '0') 1 else 0)
    }
    for (i <- 0 until n - 1) {
      val min = math.min(pre(i)(1) + suf(i + 1)(1), pre(i)(0) + suf(i + 1)(0))
      if (min < res) res = min
    }
    res
  }
}
