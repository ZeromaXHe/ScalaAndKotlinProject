package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 11:18
 * @note
 * 1414. 和为 K 的最少斐波那契数字数目 | 难度：中等 | 标签：贪心
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 *
 * 斐波那契数字定义为：
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 *
 * 示例 1：
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 *
 * 示例 2：
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 *
 * 示例 3：
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 *
 * 提示：
 * 1 <= k <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1414 {
  /**
   * 执行用时：436 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：504 / 504
   *
   * 并不是尾递归！只是递归深度不会太深
   *
   * @param k
   * @return
   */
  def findMinFibonacciNumbers(k: Int): Int = {
    var fib1 = 1L
    var fib2 = 1L
    while (fib2 < k) {
      fib2 += fib1
      fib1 = fib2 - fib1
    }
    if (fib2 == k) 1
    else {
      val newK = (k - fib1).toInt
      findMinFibonacciNumbers(newK) + 1
    }
  }
}
