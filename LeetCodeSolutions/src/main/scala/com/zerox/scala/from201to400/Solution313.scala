package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/8 11:57
 * @note
 * 313. 超级丑数 | 难度：中等 | 标签：数组、数字、动态规划
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 *
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 *
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 *
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 *
 * 提示：
 * 1 <= n <= 105
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/super-ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution313 {
  /**
   * 执行用时：756 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：62.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：87 / 87
   *
   * @param n
   * @param primes
   * @return
   */
  def nthSuperUglyNumber(n: Int, primes: Array[Int]): Int = {
    if (n == 1) return 1
    import scala.collection.mutable
    val treeMap = new mutable.TreeMap[Long, Int]
    treeMap(primes(0).toLong) = 0
    val ptr = Array.fill(primes.length)(1)
    ptr(0) = primes(0)
    for (_ <- 2 until n) {
      val min = treeMap.minAfter(0L).get
      treeMap.remove(min._1)
      treeMap(min._1 * primes(0)) = 0
      if (min._2 < primes.length - 1) {
        val next = min._1 / primes(min._2) * primes(min._2 + 1)
        if (!treeMap.contains(next)) treeMap(next) = min._2 + 1
        else treeMap(next) = (min._2 + 1) min treeMap(next)
      }
    }
    treeMap.minAfter(0L).get._1.toInt
  }
}
