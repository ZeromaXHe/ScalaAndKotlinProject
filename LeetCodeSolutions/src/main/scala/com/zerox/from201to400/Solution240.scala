package com.zerox.from201to400

import com.zerox.offer.SolutionOffer04

/**
 * @author zhuxi
 * @since 2022/7/15 15:09
 * @note
 * 240. 搜索二维矩阵 II | 难度：中等 | 标签：数组、二分查找、分治、矩阵
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
object Solution240 {
  /**
   * 执行用时：656 ms, 在所有 Scala 提交中击败了 12.50% 的用户
   * 内存消耗：61.9 MB, 在所有 Scala 提交中击败了 62.50% 的用户
   * 通过测试用例：129 / 129
   *
   * @param matrix
   * @param target
   * @return
   */
  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    SolutionOffer04.findNumberIn2DArray(matrix, target)
  }

  /**
   * 执行用时：596 ms, 在所有 Scala 提交中击败了 62.50% 的用户
   * 内存消耗：62.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：129 / 129
   *
   * @param matrix
   * @param target
   * @return
   */
  def searchMatrix_recursive(matrix: Array[Array[Int]], target: Int): Boolean = {
    SolutionOffer04.findNumberIn2DArray_recursive(matrix, target)
  }
}
