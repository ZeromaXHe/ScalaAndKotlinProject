package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/9/18 22:40
 * @note
 * 827. 最大人工岛 | 难度：困难 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 *
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 *
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 *
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 *
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/making-a-large-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution827 {
  def main(args: Array[String]): Unit = {
    println(largestIsland(Array(Array(1, 0), Array(0, 1)))) // 3
  }

  /**
   * 执行用时：1240 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：78 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：75 / 75
   *
   * @param grid
   * @return
   */
  def largestIsland(grid: Array[Array[Int]]): Int = {
    var idx = 2
    val hashMap = new scala.collection.mutable.HashMap[Int, Int]

    def dfs(i: Int, j: Int): Int = {
      grid(i)(j) = idx
      var res = 1
      if (i > 0 && grid(i - 1)(j) == 1) res += dfs(i - 1, j)
      if (j > 0 && grid(i)(j - 1) == 1) res += dfs(i, j - 1)
      if (i < grid.length - 1 && grid(i + 1)(j) == 1) res += dfs(i + 1, j)
      if (j < grid(0).length - 1 && grid(i)(j + 1) == 1) res += dfs(i, j + 1)
      res
    }

    var largest = 1
    for (i <- grid.indices; j <- grid(0).indices if grid(i)(j) == 1) {
      hashMap(idx) = dfs(i, j)
      largest = largest max hashMap(idx)
      idx += 1
    }
    val set = new scala.collection.mutable.HashSet[Int]
    for (i <- grid.indices; j <- grid(0).indices if grid(i)(j) == 0) {
      set.clear()
      if (i > 0 && grid(i - 1)(j) > 1) set += grid(i - 1)(j)
      if (j > 0 && grid(i)(j - 1) > 1) set += grid(i)(j - 1)
      if (i < grid.length - 1 && grid(i + 1)(j) > 1) set += grid(i + 1)(j)
      if (j < grid(0).length - 1 && grid(i)(j + 1) > 1) set += grid(i)(j + 1)
      // set 直接 map 的话会对结果也去重！
      largest = largest max (set.toList.map(hashMap).sum + 1)
    }
    largest
  }
}
