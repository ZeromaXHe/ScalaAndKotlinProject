package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/8/14 10:04
 * @note
 * 934. 最短的桥 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、数组、矩阵
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 *
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 *
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）
 *
 * 示例 1：
 * 输入：A = [[0,1],[1,0]]
 * 输出：1
 *
 * 示例 2：
 * 输入：A = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 *
 * 示例 3：
 * 输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 * 提示：
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-bridge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution934 {
  def shortestBridge(grid: Array[Array[Int]]): Int = {
    var findingFirstLand = true
    for (i <- grid.indices; j <- grid(0).indices if findingFirstLand) {
      if (grid(i)(j) == 1) {
        findingFirstLand = false
        dfs[Int](grid, i, j, elem => elem == 1, (g, x, y) => g(x)(y) = -1)
      }
    }
    val queue1 = new scala.collection.mutable.Queue[(Int, Int)]
    val queue2 = new scala.collection.mutable.Queue[(Int, Int)]
    1
  }

  private def dfs[T](grid: Array[Array[T]], x: Int, y: Int, valid: T => Boolean, exec: (Array[Array[T]], Int, Int) => Unit): Unit = {
    exec(grid, x, y)
    if (x + 1 < grid.length && valid(grid(x + 1)(y))) dfs(grid, x + 1, y, valid, exec)
    if (x - 1 >= 0 && valid(grid(x - 1)(y))) dfs(grid, x - 1, y, valid, exec)
    if (y + 1 < grid(0).length && valid(grid(x)(y + 1))) dfs(grid, x, y + 1, valid, exec)
    if (y - 1 >= 0 && valid(grid(x)(y - 1))) dfs(grid, x, y - 1, valid, exec)
  }
}
