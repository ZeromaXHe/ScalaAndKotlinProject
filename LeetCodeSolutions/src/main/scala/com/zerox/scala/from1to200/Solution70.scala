package com.zerox.scala.from1to200

import com.zerox.scala.offer.SolutionOffer10_II

/**
 * @author zhuxi
 * @since 2022/7/15 17:55
 * @note
 * 70. 爬楼梯 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 * 1 <= n <= 45
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution70 {
  def main(args: Array[String]): Unit = {
    // climbStairs(44) = climbStairs(42) + climbStairs(43)
    println(701408733 + 433494437)
    println((701408733 + 433494437) % 1000000007)
  }

  /**
   * 执行用时：480 ms, 在所有 Scala 提交中击败了 23.08% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 15.38% 的用户
   * 通过测试用例：45 / 45
   *
   * @param n
   * @return
   */
  def climbStairs(n: Int): Int = {
    /// 和剑指 Offer 10-II 其实不一样，不需要除 1e9 + 7
    // SolutionOffer10_II.numWays(n)
    if (n <= 1) 1
    else climbStairs(n, 1, 1)
  }

  private def climbStairs(n: Int, a: Int, b: Int): Int = {
    if (n <= 1) b
    else climbStairs(n - 1, b, a + b)
  }
}
