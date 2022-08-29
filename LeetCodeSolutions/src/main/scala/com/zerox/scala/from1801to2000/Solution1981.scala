package com.zerox.scala.from1801to2000

/**
 * @author zhuxi
 * @since 2022/8/29 15:59
 * @note
 * 1981. 最小化目标值与所选元素的差 | 难度：中等 | 标签：数组、动态规划、矩阵
 * 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
 *
 * 从矩阵的 每一行 中选择一个整数，你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。
 *
 * 返回 最小的绝对差 。
 *
 * a 和 b 两数字的 绝对差 是 a - b 的绝对值。
 *
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * 输出：0
 * 解释：一种可能的最优选择方案是：
 * - 第一行选出 1
 * - 第二行选出 5
 * - 第三行选出 7
 * 所选元素的和是 13 ，等于目标值，所以绝对差是 0 。
 *
 * 示例 2：
 * 输入：mat = [[1],[2],[3]], target = 100
 * 输出：94
 * 解释：唯一一种选择方案是：
 * - 第一行选出 1
 * - 第二行选出 2
 * - 第三行选出 3
 * 所选元素的和是 6 ，绝对差是 94 。
 *
 * 示例 3：
 * 输入：mat = [[1,2,9,8,7]], target = 6
 * 输出：1
 * 解释：最优的选择方案是选出第一行的 7 。
 * 绝对差是 1 。
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1981 {
  /**
   * 执行用时：796 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：81 / 81
   *
   * 参考题解做的
   *
   * @param mat
   * @param target
   * @return
   */
  def minimizeTheDifference(mat: Array[Array[Int]], target: Int): Int = {
    var maxsum = 0
    var f = Array[Boolean](true)
    for (i <- mat.indices) {
      val max = mat(i).max
      val g = new Array[Boolean](maxsum + max + 1)
      for (x <- mat(i); j <- x to maxsum + x) {
        g(j) |= f(j - x)
      }
      f = g
      maxsum += max
    }
    var ans = Int.MaxValue
    for (i <- 0 to maxsum if f(i) && math.abs(i - target) < ans) {
      ans = math.abs(i - target)
    }
    ans
  }

  def minimizeTheDifference_timeout(mat: Array[Array[Int]], target: Int): Int = {
    val sortMat = mat.map(_.sorted)
    var min = Int.MaxValue - 5000
    var sum = 0

    def dfs(i: Int): Unit = {
      if (sum > target + min) return
      if (i == sortMat.length) {
        val diff = math.abs(target - sum)
        if (diff < min) min = diff
        return
      }
      for (j <- sortMat(0).indices) {
        sum += sortMat(i)(j)
        dfs(i + 1)
        sum -= sortMat(i)(j)
      }
    }

    dfs(0)
    min
  }

}
