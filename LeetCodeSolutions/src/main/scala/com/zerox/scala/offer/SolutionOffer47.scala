package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/9 17:23
 * @note
 * 剑指 Offer 47. 礼物的最大价值 | 难度：中等 | 标签：数组、动态规划、矩阵
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer47 {
  def main(args: Array[String]): Unit = {
    println(maxValue(Array(Array(1, 3, 1), Array(1, 5, 1), Array(4, 2, 1))) == 12)
    println(maxValue(Array(Array(1, 2, 5), Array(3, 2, 1))) == 9)
  }

  /**
   * 执行用时：552 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗： 56.4 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：61 / 61
   *
   * @param grid
   * @return
   */
  def maxValue(grid: Array[Array[Int]]): Int = {
    val dp = new Array[Int](grid(0).length)
    for (i <- grid.indices) {
      dp(0) += grid(i)(0)
      for (j <- 1 until grid(0).length) {
        dp(j) = math.max(dp(j - 1), dp(j)) + grid(i)(j)
      }
    }
    dp(dp.length - 1)
  }
}
