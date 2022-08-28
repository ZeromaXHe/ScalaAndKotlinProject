package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/8/28 9:10
 * @note
 * 172. 阶乘后的零 | 难度：中等 | 标签：数学
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：0
 *
 * 提示：
 * 0 <= n <= 104
 *
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/factorial-trailing-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution172 {
  /**
   * 执行用时：416 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 75.00% 的用户
   * 通过测试用例：500 / 500
   *
   * @param n
   * @return
   */
  def trailingZeroes(n: Int): Int = {
    var res = 0
    var test = 5
    while (test <= n) {
      var temp = test
      while (temp > 0 && temp % 5 == 0) {
        temp /= 5
        res += 1
      }
      test += 5
    }
    res
  }

  /**
   * 执行用时：428 ms, 在所有 Scala 提交中击败了 25.00% 的用户
   * 内存消耗：51.2 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：500 / 500
   *
   * @param n
   * @return
   */
  private def trailingZeroes_Ologn(n: Int): Int = {
    var res = 0
    var vn = n
    while (vn > 0) {
      vn /= 5
      res += vn
    }
    res
  }
}
