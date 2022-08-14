package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/7/20 9:36
 * @note
 * 1260. 二维网格迁移 | 难度：简单 | 标签：数组、矩阵、模拟
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 *
 * 每次「迁移」操作将会引发下述活动：
 *
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 *
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 *
 * 示例 2：
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 *
 * 示例 3：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shift-2d-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1260 {
  def main(args: Array[String]): Unit = {
    println(shiftGrid(Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9)), 1))
    println(shiftGrid(Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9)), 0))
    println(shiftGrid(Array(Array(1), Array(2), Array(3), Array(4), Array(7), Array(6), Array(5)), 23))
  }

  /**
   * 执行用时：708 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：107 / 107
   *
   * @param grid
   * @param k
   * @return
   */
  def shiftGrid(grid: Array[Array[Int]], k: Int): List[List[Int]] = {
    val total = grid.length * grid(0).length
    val tuple = grid.flatten.toList.splitAt(total - k % total)
    (tuple._2 ::: tuple._1).grouped(grid(0).length).toList
  }
}
