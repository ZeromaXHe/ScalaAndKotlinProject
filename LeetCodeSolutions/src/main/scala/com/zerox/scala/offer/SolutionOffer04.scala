package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/15 15:04
 * @note
 * 剑指 Offer 04. 二维数组中的查找 | 难度：中等 | 标签：数组、二分查找、分治、矩阵
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 *
 * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer04 {
  /**
   * 执行用时：636 ms, 在所有 Scala 提交中击败了 16.67% 的用户
   * 内存消耗：62.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：129 / 129
   *
   * @param matrix
   * @param target
   * @return
   */
  def findNumberIn2DArray(matrix: Array[Array[Int]], target: Int): Boolean = {
    val n = matrix.length
    if (n == 0) return false
    val m = matrix(0).length
    if (m == 0) return false
    var i = n - 1
    var j = 0
    while (i >= 0 && j < m) {
      if (target == matrix(i)(j)) return true
      if (target < matrix(i)(j)) i -= 1
      else j += 1
    }
    false
  }

  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 83.33% 的用户
   * 内存消耗：62 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：129 / 129
   *
   * @param matrix
   * @param target
   * @return
   */
  def findNumberIn2DArray_recursive(matrix: Array[Array[Int]], target: Int): Boolean = {
    @scala.annotation.tailrec
    def f(row: Int, col: Int): Boolean =
      if (row >= matrix.length || col < 0) false
      else matrix(row)(col).compareTo(target) match {
        case 0 => true
        case 1 => f(row, col - 1)
        case -1 => f(row + 1, col)
      }

    if (matrix.isEmpty) false
    else f(0, matrix.head.length - 1)
  }
}
