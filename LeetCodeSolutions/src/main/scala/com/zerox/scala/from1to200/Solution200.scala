package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/8/14 16:35
 * @note
 * 200. 岛屿数量 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution200 {
  /**
   * 执行用时：652 ms, 在所有 Scala 提交中击败了 15.38% 的用户
   * 内存消耗：61.2 MB, 在所有 Scala 提交中击败了 61.54% 的用户
   * 通过测试用例：49 / 49
   *
   * @param grid
   * @return
   */
  def numIslands(grid: Array[Array[Char]]): Int = {
    var result = 0
    for (i <- grid.indices; j <- grid(0).indices if grid(i)(j) == '1') {
      dfs(grid, i, j)
      result += 1
    }
    result
  }

  private def dfs(grid: Array[Array[Char]], i: Int, j: Int): Unit = {
    grid(i)(j) = '0'
    if (i - 1 >= 0 && grid(i - 1)(j) == '1') dfs(grid, i - 1, j)
    if (j - 1 >= 0 && grid(i)(j - 1) == '1') dfs(grid, i, j - 1)
    if (i + 1 < grid.length && grid(i + 1)(j) == '1') dfs(grid, i + 1, j)
    if (j + 1 < grid(0).length && grid(i)(j + 1) == '1') dfs(grid, i, j + 1)
  }
}
