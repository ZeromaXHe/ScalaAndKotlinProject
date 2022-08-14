package com.zerox.scala.from2201to2400

/**
 * @author ZeromaXHe
 * @since 2022/7/2 21:50
 * @note
 * 2319. 判断矩阵是否是一个 X 矩阵 | 难度：简单 | 标签：数组、矩阵
 * 如果一个正方形矩阵满足下述 全部 条件，则称之为一个 X 矩阵 ：
 *
 * 矩阵对角线上的所有元素都 不是 0
 * 矩阵中所有其他元素都是 0
 * 给你一个大小为 n x n 的二维整数数组 grid ，表示一个正方形矩阵。如果 grid 是一个 X 矩阵 ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
 * 输出：true
 * 解释：矩阵如上图所示。
 * X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
 * 因此，grid 是一个 X 矩阵。
 *
 * 示例 2：
 * 输入：grid = [[5,7,0],[0,3,1],[0,5,0]]
 * 输出：false
 * 解释：矩阵如上图所示。
 * X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
 * 因此，grid 不是一个 X 矩阵。
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 100
 * 0 <= grid[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-matrix-is-x-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2319 {
  /**
   * 执行用时：676 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：84 / 84
   *
   * @param grid
   * @return
   */
  def checkXMatrix(grid: Array[Array[Int]]): Boolean = {
    for (i <- grid.indices;
         j <- grid.indices) {
      if (grid(i)(j) != 0 && i != j && i != grid.length - 1 - j) {
        return false
      }
      if (grid(i)(j) == 0 && (i == j || i == grid.length - 1 - j)) {
        return false
      }
    }
    true
  }
}
