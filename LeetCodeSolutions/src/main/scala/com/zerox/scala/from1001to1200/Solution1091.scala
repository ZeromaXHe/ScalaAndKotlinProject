package com.zerox.scala.from1001to1200

/**
 * @author ZeromaXHe
 * @since 2022/8/13 9:53
 * @note
 * 1091. 二进制矩阵中的最短路径 | 难度：中等 | 标签：广度优先搜索、数组、矩阵
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 *
 * 示例 2：
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 *
 * 示例 3：
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-path-in-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1091 {
  /**
   * 执行用时：652 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：89 / 89
   *
   * @param grid
   * @return
   */
  def shortestPathBinaryMatrix(grid: Array[Array[Int]]): Int = {
    val n = grid.length
    if (grid(0)(0) == 1) return -1
    if (n == 1) return 1
    val visit = Array.ofDim[Boolean](n, n)
    visit(0)(0) = true
    val queue = new scala.collection.mutable.Queue[(Int, Int)]
    queue.enqueue((0, 0))
    var dist = 1
    while (queue.nonEmpty) {
      dist += 1
      var c = queue.size
      while (c > 0) {
        val (x, y) = queue.dequeue()
        if (enqueueValidXY(grid, visit, x + 1, y + 1, n, queue) ||
          enqueueValidXY(grid, visit, x + 1, y, n, queue) ||
          enqueueValidXY(grid, visit, x + 1, y - 1, n, queue) ||
          enqueueValidXY(grid, visit, x, y + 1, n, queue) ||
          enqueueValidXY(grid, visit, x, y, n, queue) ||
          enqueueValidXY(grid, visit, x, y - 1, n, queue) ||
          enqueueValidXY(grid, visit, x - 1, y + 1, n, queue) ||
          enqueueValidXY(grid, visit, x - 1, y, n, queue) ||
          enqueueValidXY(grid, visit, x - 1, y - 1, n, queue)) return dist
        c -= 1
      }
    }
    -1
  }

  private def enqueueValidXY(grid: Array[Array[Int]], visit: Array[Array[Boolean]], x: Int, y: Int, n: Int,
                             queue: scala.collection.mutable.Queue[(Int, Int)]): Boolean = {
    if (x >= 0 && y >= 0 && x < n && y < n && !visit(x)(y) && grid(x)(y) == 0) {
      queue.enqueue((x, y))
      visit(x)(y) = true
      if (x == n - 1 && y == n - 1) return true
    }
    false
  }
}
