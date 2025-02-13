package com.zerox.scala.from1401to1600

/**
 * @author ZeromaXHe
 * @since 2022/9/4 10:19
 * @note
 * 1582. 二进制矩阵中的特殊位置 | 难度：简单 | 标签：数组、矩阵
 * 给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
 *
 * 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
 *
 * 示例 1：
 * 输入：mat = [[1,0,0],
 * |           [0,0,1],
 * |           [1,0,0]]
 * 输出：1
 * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 *
 * 示例 2：
 * 输入：mat = [[1,0,0],
 * |           [0,1,0],
 * |           [0,0,1]]
 * 输出：3
 * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 *
 * 示例 3：
 * 输入：mat = [[0,0,0,1],
 * |           [1,0,0,0],
 * |           [0,1,1,0],
 * |           [0,0,0,0]]
 * 输出：2
 *
 * 示例 4：
 * 输入：mat = [[0,0,0,0,0],
 * |           [1,0,0,0,0],
 * |           [0,1,0,0,0],
 * |           [0,0,1,0,0],
 * |           [0,0,0,1,1]]
 * 输出：3
 *
 * 提示：
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] 是 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/special-positions-in-a-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1582 {
  /**
   * 执行用时：584 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：95 / 95
   *
   * @param mat
   * @return
   */
  def numSpecial(mat: Array[Array[Int]]): Int = {
    val row1 = new Array[Int](mat.length)
    val col1 = new Array[Int](mat(0).length)
    for (i <- mat.indices; j <- mat(0).indices if mat(i)(j) == 1) {
      row1(i) += 1
      col1(j) += 1
    }
    var res = 0
    for (i <- mat.indices; j <- mat(0).indices if mat(i)(j) == 1 && row1(i) == 1 && col1(j) == 1) {
      res += 1
    }
    res
  }
}
