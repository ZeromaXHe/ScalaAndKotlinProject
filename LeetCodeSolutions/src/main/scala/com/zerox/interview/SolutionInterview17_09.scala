package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/9 16:34
 * @note
 * 面试题 17.09. 第 k 个数 | 难度：中等 | 标签：哈希表、数学、动态规划、堆（优先队列）
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_09 {
  def main(args: Array[String]): Unit = {
    println(getKthMagicNumber(100) == 33075)
  }

  /**
   * 执行用时：404 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：20 / 20
   *
   * 提交的是直接把 3，5，7 内联到 nthUglyNumber 方法的代码
   * 和 264 题、剑指 Offer 49 题这些丑数思路相同，所以提取出了一个 nthUglyNumber 方法
   *
   * @param k
   * @return
   */
  def getKthMagicNumber(k: Int): Int = {
    nthUglyNumber(k, 3, 5, 7)
  }

  def nthUglyNumber(n: Int, p1: Int, p2: Int, p3: Int): Int = {
    val dp = new Array[Int](n)
    val ptr = new Array[Int](3)
    dp(0) = 1
    for (i <- 1 until n) {
      dp(i) = math.min(math.min(dp(ptr(0)) * p1, dp(ptr(1)) * p2), dp(ptr(2)) * p3)
      // if 语句必须都并列执行，如果使用 else if 会导致没有去重
      if (dp(i) == dp(ptr(0)) * p1) ptr(0) += 1
      if (dp(i) == dp(ptr(1)) * p2) ptr(1) += 1
      if (dp(i) == dp(ptr(2)) * p3) ptr(2) += 1
    }
    dp(n - 1)
  }
}
