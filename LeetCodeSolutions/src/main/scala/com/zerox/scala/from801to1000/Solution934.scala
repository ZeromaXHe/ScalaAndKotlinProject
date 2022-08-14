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
  def main(args: Array[String]): Unit = {
    println(shortestBridge(Array(
      Array(0, 1, 0, 0, 0),
      Array(0, 1, 0, 1, 1),
      Array(0, 0, 0, 0, 1),
      Array(0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0))))
  }

  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：97 / 97
   *
   * @param grid
   * @return
   */
  def shortestBridge(grid: Array[Array[Int]]): Int = {
    var findingFirstLand = true
    var findingSecondLand = true
    val queue1 = new scala.collection.mutable.Queue[(Int, Int)]
    val queue2 = new scala.collection.mutable.Queue[(Int, Int)]
    for (i <- grid.indices; j <- grid(0).indices if findingFirstLand || findingSecondLand) {
      if (findingFirstLand) {
        if (grid(i)(j) == 1) {
          findingFirstLand = false
          dfs[Int](grid, i, j, elem => elem == 1,
            (g, x, y) => {
              g(x)(y) = -1
              enqueueShore(queue1, g, x, y)
            })
        }
      } else {
        if (grid(i)(j) == 1) {
          findingSecondLand = false
          dfs[Int](grid, i, j, elem => elem == 1,
            (g, x, y) => {
              g(x)(y) = 2
              enqueueShore(queue2, g, x, y)
            })
        }
      }
    }
    while (queue1.nonEmpty && queue2.nonEmpty) {
      var size1 = queue1.size
      while (size1 > 0) {
        val (x, y) = queue1.dequeue()
        val res = enqueueAroundSeaOrReturnFoundLand(queue1, grid, x, y, grid(x)(y) - 1)
        if (res != 0) return res - grid(x)(y) - 3
        size1 -= 1
      }
      var size2 = queue2.size
      while (size2 > 0) {
        val (x, y) = queue2.dequeue()
        val res = enqueueAroundSeaOrReturnFoundLand(queue2, grid, x, y, grid(x)(y) + 1)
        if (res != 0) return grid(x)(y) - res - 3
        size2 -= 1
      }
    }
    0
  }

  private def enqueueShore(queue: scala.collection.mutable.Queue[(Int, Int)],
                           grid: Array[Array[Int]], x: Int, y: Int): Unit = {
    if (x + 1 < grid.length && grid(x + 1)(y) == 0) queue.enqueue((x, y))
    else if (x - 1 >= 0 && grid(x - 1)(y) == 0) queue.enqueue((x, y))
    else if (y + 1 < grid(0).length && grid(x)(y + 1) == 0) queue.enqueue((x, y))
    else if (y - 1 >= 0 && grid(x)(y - 1) == 0) queue.enqueue((x, y))
  }

  private def enqueueAroundSeaOrReturnFoundLand(queue: scala.collection.mutable.Queue[(Int, Int)],
                                                grid: Array[Array[Int]], x: Int, y: Int, to: Int): Int = {
    if (x + 1 < grid.length) {
      if (grid(x + 1)(y) == 0) {
        grid(x + 1)(y) = to
        queue.enqueue((x + 1, y))
      }
      else if ((grid(x + 1)(y) ^ to) >>> 31 == 1) return grid(x + 1)(y)
    }
    if (x - 1 >= 0) {
      if (grid(x - 1)(y) == 0) {
        grid(x - 1)(y) = to
        queue.enqueue((x - 1, y))
      }
      else if ((grid(x - 1)(y) ^ to) >>> 31 == 1) return grid(x - 1)(y)
    }
    if (y + 1 < grid(0).length) {
      if (grid(x)(y + 1) == 0) {
        grid(x)(y + 1) = to
        queue.enqueue((x, y + 1))
      }
      else if ((grid(x)(y + 1) ^ to) >>> 31 == 1) return grid(x)(y + 1)
    }
    if (y - 1 >= 0) {
      if (grid(x)(y - 1) == 0) {
        grid(x)(y - 1) = to
        queue.enqueue((x, y - 1))
      }
      else if ((grid(x)(y - 1) ^ to) >>> 31 == 1) return grid(x)(y - 1)
    }
    0
  }

  private def dfs[T](grid: Array[Array[T]], x: Int, y: Int, valid: T => Boolean,
                     exec: (Array[Array[T]], Int, Int) => Unit): Unit = {
    exec(grid, x, y)
    if (x + 1 < grid.length && valid(grid(x + 1)(y))) dfs(grid, x + 1, y, valid, exec)
    if (x - 1 >= 0 && valid(grid(x - 1)(y))) dfs(grid, x - 1, y, valid, exec)
    if (y + 1 < grid(0).length && valid(grid(x)(y + 1))) dfs(grid, x, y + 1, valid, exec)
    if (y - 1 >= 0 && valid(grid(x)(y - 1))) dfs(grid, x, y - 1, valid, exec)
  }
}
