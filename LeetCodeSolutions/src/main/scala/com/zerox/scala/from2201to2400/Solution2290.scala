package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/29 10:43
 * @note
 * 2290. 到达角落需要移除障碍物的最小数目 | 难度：困难 | 标签：广度优先搜索、图、数组、矩阵、最短路、堆（优先队列）
 * 给你一个下标从 0 开始的二维整数数组 grid ，数组大小为 m x n 。每个单元格都是两个值之一：
 *
 * 0 表示一个 空 单元格，
 * 1 表示一个可以移除的 障碍物 。
 * 你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
 *
 * 现在你需要从左上角 (0, 0) 移动到右下角 (m - 1, n - 1) ，返回需要移除的障碍物的 最小 数目。
 *
 * 示例 1：
 * 输入：grid = [[0,1,1],[1,1,0],[1,1,0]]
 * 输出：2
 * 解释：可以移除位于 (0, 1) 和 (0, 2) 的障碍物来创建从 (0, 0) 到 (2, 2) 的路径。
 * 可以证明我们至少需要移除两个障碍物，所以返回 2 。
 * 注意，可能存在其他方式来移除 2 个障碍物，创建出可行的路径。
 *
 * 示例 2：
 * 输入：grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * 输出：0
 * 解释：不移除任何障碍物就能从 (0, 0) 到 (2, 4) ，所以返回 0 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * grid[i][j] 为 0 或 1
 * grid[0][0] == grid[m - 1][n - 1] == 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2290 {
  private final val dirs = Array(Array(-1, 0), Array(1, 0), Array(0, -1), Array(0, 1))

  /**
   * 执行用时：1288 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：87.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：54 / 54
   *
   * 参考题解做的
   *
   * @param grid
   * @return
   */
  def minimumObstacles(grid: Array[Array[Int]]): Int = {
    val m = grid.length
    val n = grid(0).length
    val dis = Array.fill(m)(Array.fill(n)(Int.MaxValue))
    dis(0)(0) = 0
    val q = new scala.collection.mutable.ListBuffer[Array[Int]]
    q.prepend(Array(0, 0))
    while (q.nonEmpty) {
      val Array(x, y) = q.remove(0)
      for (d <- dirs) {
        val nx = x + d(0)
        val ny = y + d(1)
        if (0 <= nx && nx < m && 0 <= ny && ny < n) {
          val g = grid(nx)(ny)
          if (dis(x)(y) + g < dis(nx)(ny)) {
            dis(nx)(ny) = dis(x)(y) + g
            if (g == 0) q.prepend(Array(nx, ny))
            else q.append(Array(nx, ny))
          }
        }
      }
    }
    dis(m - 1)(n - 1)
  }
}
