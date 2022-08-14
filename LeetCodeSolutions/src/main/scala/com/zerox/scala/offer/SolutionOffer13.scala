package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/8/3 11:04
 * @note
 * 面试题13. 机器人的运动范围 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、动态规划
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 *
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer13 {
  /**
   * 执行用时：448 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：51 / 51
   *
   * @param m
   * @param n
   * @param k
   * @return
   */
  def movingCount(m: Int, n: Int, k: Int): Int = {
    val visit = Array.ofDim[Boolean](m, n)
    dfs(0, 0, visit, k)
  }

  private def dfs(x: Int, y: Int, visit: Array[Array[Boolean]], k: Int): Int = {
    visit(x)(y) = true
    var count = 1
    if (x + 1 < visit.length && !visit(x + 1)(y) && sumDigit(x + 1, y) <= k) count += dfs(x + 1, y, visit, k)
    if (x - 1 >= 0 && !visit(x - 1)(y) && sumDigit(x - 1, y) <= k) count += dfs(x - 1, y, visit, k)
    if (y + 1 < visit(0).length && !visit(x)(y + 1) && sumDigit(x, y + 1) <= k) count += dfs(x, y + 1, visit, k)
    if (y - 1 >= 0 && !visit(x)(y - 1) && sumDigit(x, y - 1) <= k) count += dfs(x, y - 1, visit, k)
    count
  }

  private def sumDigit(x: Int, y: Int): Int = {
    var sum = 0
    var dx = x
    while (dx > 0) {
      sum += dx % 10
      dx /= 10
    }
    var dy = y
    while (dy > 0) {
      sum += dy % 10
      dy /= 10
    }
    sum
  }
}
