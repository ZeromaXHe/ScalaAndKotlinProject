package com.zerox.scala.offerII

/**
 * @author zhuxi
 * @since 2022/6/25 9:39
 * @note
 * 剑指 Offer II 091. 粉刷房子 | 难度：中等 | 标签：数组、动态规划
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 *
 * 请计算出粉刷完所有房子最少的花费成本。
 *
 * 示例 1：
 *
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 *
 * 示例 2：
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 *
 * 提示:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 *
 * 注意：本题与主站 256 题相同：https://leetcode-cn.com/problems/paint-house/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/JEj789
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOfferII_91 {
  /**
   * 执行用时：584 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：100 / 100
   *
   * @param costs
   * @return
   */
  def minCost(costs: Array[Array[Int]]): Int = {
    val dp = new Array[Int](3)
    for (elem <- costs) {
      val dp0 = Math.min(dp(1), dp(2)) + elem(0)
      val dp1 = Math.min(dp(0), dp(2)) + elem(1)
      val dp2 = Math.min(dp(0), dp(1)) + elem(2)
      dp(0) = dp0
      dp(1) = dp1
      dp(2) = dp2
    }
    dp.min
  }
}
