package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/9/2 17:14
 * @note
 * 2245. 转角路径的乘积中最多能有几个尾随零 | 难度：中等 | 标签：数组、矩阵、前缀和
 * 给你一个二维整数数组 grid ，大小为 m x n，其中每个单元格都含一个正整数。
 *
 * 转角路径 定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全 向水平方向 或者 向竖直方向 移动过弯（如果存在弯），而不能访问之前访问过的单元格。在过弯之后，路径应当完全朝 另一个 方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。
 *
 * 一条路径的 乘积 定义为：路径上所有值的乘积。
 *
 * 请你从 grid 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。
 *
 * 注意：
 * 水平 移动是指向左或右移动。
 * 竖直 移动是指向上或下移动。
 *
 * 示例 1：
 * 输入：grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * 输出：3
 * 解释：左侧的图展示了一条有效的转角路径。
 * 其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
 * 可以证明在这条转角路径的乘积中尾随零数目最多。
 *
 * 中间的图不是一条有效的转角路径，因为它有不止一个弯。
 * 右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
 *
 * 示例 2：
 * 输入：grid = [[4,3,2],[7,6,1],[8,8,8]]
 * 输出：0
 * 解释：网格如上图所示。
 * 不存在乘积含尾随零的转角路径。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= grid[i][j] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-trailing-zeros-in-a-cornered-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2245 {
  def main(args: Array[String]): Unit = {
    println(maxTrailingZeros(Array(
      Array(23, 17, 15, 3, 20),
      Array(8, 1, 20, 27, 11),
      Array(9, 4, 6, 2, 21),
      Array(40, 9, 1, 10, 6),
      Array(22, 7, 4, 5, 3))))
  }

  /**
   * 执行用时：1412 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：88.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：55 / 55
   *
   * @param grid
   * @return
   */
  def maxTrailingZeros(grid: Array[Array[Int]]): Int = {
    val m = grid.length
    val n = grid(0).length
    val rowPresum = Array.fill(m)(Array.fill(n + 1)(new Array[Int](2)))
    val colPresum = Array.fill(m + 1)(Array.fill(n)(new Array[Int](2)))
    for (i <- grid.indices; j <- grid(0).indices) {
      rowPresum(i)(j + 1)(0) = rowPresum(i)(j)(0)
      rowPresum(i)(j + 1)(1) = rowPresum(i)(j)(1)
      colPresum(i + 1)(j)(0) = colPresum(i)(j)(0)
      colPresum(i + 1)(j)(1) = colPresum(i)(j)(1)
      var div = grid(i)(j)
      while (div % 2 == 0) {
        rowPresum(i)(j + 1)(0) += 1
        colPresum(i + 1)(j)(0) += 1
        div /= 2
      }
      div = grid(i)(j)
      while (div % 5 == 0) {
        rowPresum(i)(j + 1)(1) += 1
        colPresum(i + 1)(j)(1) += 1
        div /= 5
      }
    }
    var maximum = 0
    for (i <- grid.indices; j <- grid(0).indices) {
      val lu = math.min(
        rowPresum(i)(j)(0) + colPresum(i + 1)(j)(0),
        rowPresum(i)(j)(1) + colPresum(i + 1)(j)(1))
      val ld = math.min(
        rowPresum(i)(j)(0) + colPresum(m)(j)(0) - colPresum(i)(j)(0),
        rowPresum(i)(j)(1) + colPresum(m)(j)(1) - colPresum(i)(j)(1))
      val ru = math.min(
        rowPresum(i)(n)(0) - rowPresum(i)(j)(0) + colPresum(i)(j)(0),
        rowPresum(i)(n)(1) - rowPresum(i)(j)(1) + colPresum(i)(j)(1))
      val rd = math.min(
        rowPresum(i)(n)(0) - rowPresum(i)(j + 1)(0) + colPresum(m)(j)(0) - colPresum(i)(j)(0),
        rowPresum(i)(n)(1) - rowPresum(i)(j + 1)(1) + colPresum(m)(j)(1) - colPresum(i)(j)(1))
      maximum = maximum max lu max ld max ru max rd
    }
    for (i <- grid.indices) {
      maximum = maximum max rowPresum(i)(n).min
    }
    for (j <- grid(0).indices) {
      maximum = maximum max colPresum(m)(j).min
    }
    maximum
  }
}
