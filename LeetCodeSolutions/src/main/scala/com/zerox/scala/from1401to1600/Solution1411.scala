package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 10:59
 * @note
 * 1411. 给 N x 3 网格图涂色的方案数 | 难度：困难 | 标签：动态规划
 * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
 *
 * 给你网格图的行数 n 。
 *
 * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：12
 * 解释：总共有 12 种可行的方法：
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：54
 *
 * 示例 3：
 * 输入：n = 3
 * 输出：246
 *
 * 示例 4：
 * 输入：n = 7
 * 输出：106494
 *
 * 示例 5：
 * 输入：n = 5000
 * 输出：30228214
 *
 * 提示：
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1411 {
  /**
   * 执行用时：424 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：50 / 50
   *
   * @param n
   * @return
   */
  def numOfWays(n: Int): Int = {
    val MOD = (1e9 + 7).toLong
    var vn = n - 1
    var sum3 = 6L
    var sum2 = 6L
    while (vn > 0) {
      val newSum3 = (sum2 * 2 + sum3 * 2) % MOD
      val newSum2 = (sum2 * 3 + sum3 * 2) % MOD
      sum2 = newSum2
      sum3 = newSum3
      vn -= 1
    }
    ((sum2 + sum3) % MOD).toInt
  }
}
