package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/8/23 10:24
 * @note
 * 1292. 元素和小于等于阈值的正方形的最大边长 | 难度：中等 | 标签：数组、二分查找、矩阵、前缀和
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 *
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *
 * 示例 1：
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 *
 * 示例 2：
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 104
 * 0 <= threshold <= 105 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1292 {
  var preSum: Array[Array[Int]] = null

  /**
   * 执行用时：1640 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：66.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：46 / 46
   *
   * @param mat
   * @param threshold
   * @return
   */
  def maxSideLength(mat: Array[Array[Int]], threshold: Int): Int = {
    val m = mat.length
    val n = mat(0).length
    preSum = Array.fill(m + 1)(new Array[Int](n + 1))
    for (i <- mat.indices; j <- mat(0).indices) {
      preSum(i + 1)(j + 1) = preSum(i + 1)(j) + preSum(i)(j + 1) - preSum(i)(j) + mat(i)(j)
    }
    val r = math.min(m, n)
    var res = 0
    for (i <- mat.indices; j <- mat(0).indices) {
      var continue = true
      for (c <- res + 1 to r if continue) {
        if (i + c - 1 < m && j + c - 1 < n && sumRegion(i, j, i + c - 1, j + c - 1) <= threshold) res += 1
        else continue = false
      }
    }
    res
  }

  def sumRegion(row1: Int, col1: Int, row2: Int, col2: Int) =
    preSum(row2 + 1)(col2 + 1) - preSum(row2 + 1)(col1) - preSum(row1)(col2 + 1) + preSum(row1)(col1)
}
