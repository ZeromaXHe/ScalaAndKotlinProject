package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/9/1 11:12
 * @note
 * 1210. 穿过迷宫的最少移动次数 | 难度：困难 | 标签：广度优先搜索、数组、矩阵
 * 你还记得那条风靡全球的贪吃蛇吗？
 *
 * 我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
 *
 * 每次移动，蛇可以这样走：
 *
 * 如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
 *
 * 如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
 *
 * 返回蛇抵达目的地所需的最少移动次数。
 *
 * 如果无法到达目的地，请返回 -1。
 *
 * 示例 1：
 * 输入：grid = [[0,0,0,0,0,1],
 * |            [1,1,0,0,1,0],
 * |            [0,0,0,0,1,1],
 * |            [0,0,1,0,1,0],
 * |            [0,1,1,0,0,0],
 * |            [0,1,1,0,0,0]]
 * 输出：11
 * 解释：
 * 一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
 *
 * 示例 2：
 * 输入：grid = [[0,0,1,1,1,1],
 * |            [0,0,0,0,1,1],
 * |            [1,1,0,0,0,1],
 * |            [1,1,1,0,0,1],
 * |            [1,1,1,0,0,1],
 * |            [1,1,1,0,0,0]]
 * 输出：9
 *
 * 提示：
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * 蛇保证从空单元格开始出发。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-moves-to-reach-target-with-rotations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1210 {
  /**
   * 执行用时：624 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：42 / 42
   *
   * @param grid
   * @return
   */
  def minimumMoves(grid: Array[Array[Int]]): Int = {
    val n = grid.length
    val visit = Array.ofDim[Boolean](n, n, 2)
    val queue = new scala.collection.mutable.Queue[(Int, Int, Boolean)]
    queue.enqueue((0, 0, false))
    visit(0)(0)(0) = true
    var move = 0
    while (queue.nonEmpty) {
      var size = queue.size
      while (size > 0) {
        val (x, y, downward) = queue.dequeue()
        if (x == n - 1 && y == n - 2 && !downward) return move
        enqueueAvaliableNextMove(grid, x, y, downward, queue, visit)
        size -= 1
      }
      move += 1
    }
    -1
  }

  private def enqueueAvaliableNextMove(grid: Array[Array[Int]], x: Int, y: Int, downward: Boolean,
                                       queue: scala.collection.mutable.Queue[(Int, Int, Boolean)],
                                       visit: Array[Array[Array[Boolean]]]): Unit = {
    val n = grid.length
    if (downward) {
      if (x + 2 < n && !visit(x + 1)(y)(1) && grid(x + 2)(y) == 0) {
        visit(x + 1)(y)(1) = true
        queue.enqueue((x + 1, y, true))
      }
      if (y + 1 < n && grid(x)(y + 1) == 0 && grid(x + 1)(y + 1) == 0) {
        if (!visit(x)(y)(0)) {
          visit(x)(y)(0) = true
          queue.enqueue((x, y, false))
        }
        if (!visit(x)(y + 1)(1)) {
          visit(x)(y + 1)(1) = true
          queue.enqueue((x, y + 1, true))
        }
      }
    } else {
      if (y + 2 < n && !visit(x)(y + 1)(0) && grid(x)(y + 2) == 0) {
        visit(x)(y + 1)(0) = true
        queue.enqueue((x, y + 1, false))
      }
      if (x + 1 < n && grid(x + 1)(y) == 0 && grid(x + 1)(y + 1) == 0) {
        if (!visit(x)(y)(1)) {
          visit(x)(y)(1) = true
          queue.enqueue((x, y, true))
        }
        if (!visit(x + 1)(y)(0)) {
          visit(x + 1)(y)(0) = true
          queue.enqueue((x + 1, y, false))
        }
      }
    }
  }
}
