package com.zerox.scala.from201to400

import com.zerox.scala.aider.DynamicProgrammingUtils.cachedArray2D
import com.zerox.scala.aider.GraphUtils.dfs

/**
 * @author zhuxi
 * @since 2022/9/9 14:58
 * @note
 * 329. 矩阵中的最长递增路径 | 难度：困难 | 标签：深度优先搜索、广度优先搜索、图、拓扑排序、记忆化搜索、数组、动态规划、矩阵
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution329 {
  def longestIncreasingPath_timeout(matrix: Array[Array[Int]]): Int = {
    val dp = Array.fill(matrix.length)(Array.fill(matrix(0).length)(0))
    var max = 1
    for (i <- matrix.indices; j <- matrix(0).indices) {
      val res = dfs[Int](matrix, i, j,
        valid = (ni, nj, oi, oj) => matrix(ni)(nj) > matrix(oi)(oj),
        exec = (_, _) => 1,
        reduceRes = (r, dfsRes) => r max (dfsRes + 1),
        exitCondition = dp(_)(_) > 0,
        exitRes = dp(_)(_),
        postExec = (oi, oj, r) => {
          dp(oi)(oj) = r
          r
        }
      )
      if (res > max) max = res
    }
    max
  }

  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 75.00% 的用户
   * 内存消耗：55.2 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：138 / 138
   *
   * @param matrix
   * @return
   */
  def longestIncreasingPath(matrix: Array[Array[Int]]): Int = {
    val dp = Array.fill(matrix.length)(Array.fill(matrix(0).length)(0))
    var max = 1

    def dfs(i: Int, j: Int): Int = {
      if (dp(i)(j) >= 1) return dp(i)(j)
      var res = 1
      if (i - 1 >= 0 && matrix(i - 1)(j) > matrix(i)(j)) {
        res = res max (dfs(i - 1, j) + 1)
      }
      if (i + 1 < matrix.length && matrix(i + 1)(j) > matrix(i)(j)) {
        res = res max (dfs(i + 1, j) + 1)
      }
      if (j - 1 >= 0 && matrix(i)(j - 1) > matrix(i)(j)) {
        res = res max (dfs(i, j - 1) + 1)
      }
      if (j + 1 < matrix(0).length && matrix(i)(j + 1) > matrix(i)(j)) {
        res = res max (dfs(i, j + 1) + 1)
      }
      dp(i)(j) = res
      res
    }

    for (i <- matrix.indices; j <- matrix(0).indices) {
      val res = dfs(i, j)
      if (res > max) max = res
    }
    max
  }
}
