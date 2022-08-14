package com.zerox.scala.from1001to1200

/**
 * @author zhuxi
 * @since 2022/6/30 9:58
 * @note
 * 1175. 质数排列 | 难度：简单　| 标签：数学
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 *
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 *
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 *
 * 示例 2：
 * 输入：n = 100
 * 输出：682289015
 *
 * 提示：
 * 1 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prime-arrangements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1175 {
  def main(args: Array[String]): Unit = {
    println(numPrimeArrangements(5) == 12)
  }

  /**
   * 执行用时：468 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：100 / 100
   *
   * @param n
   * @return
   */
  def numPrimeArrangements(n: Int): Int = {
    val primeCount = (for (i <- 2 to n if isPrime(i)) yield 1).sum
    (factorial(primeCount) * factorial(n - primeCount) % 1000000007).toInt
  }

  private def isPrime(n: Int): Boolean = {
    var test = 2
    while (test * test <= n) {
      if (n % test == 0) return false
      test += 1
    }
    true
  }

  private def factorial(n: Int): Long = {
    var result: Long = 1L
    var i = 2
    while (i <= n) {
      result *= i
      result %= 1000000007
      i += 1
    }
    result
  }
}
