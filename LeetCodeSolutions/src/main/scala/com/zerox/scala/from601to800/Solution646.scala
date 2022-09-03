package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/9/3 12:41
 * @note
 * 646. 最长数对链 | 难度：中等 | 标签：贪心、数组、动态规划、排序
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例：
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 *
 * 提示：
 * 给出数对的个数在 [1, 1000] 范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-length-of-pair-chain
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution646 {
  /**
   * 执行用时：656 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：205 / 205
   *
   * @param pairs
   * @return
   */
  def findLongestChain(pairs: Array[Array[Int]]): Int = {
    val sort = pairs.sortBy(a => (a(1), -a(0)))
    var end = sort(0)(1)
    var count = 1
    for (i <- 1 until sort.length) {
      if (sort(i)(0) > end) {
        end = sort(i)(1)
        count += 1
      }
    }
    count
  }
}
