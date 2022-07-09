package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/9 19:33
 * @note
 * 400. 第 N 位数字 | 难度：中等 | 标签：数学、二分查找
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *
 * 提示：
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution400 {
  /**
   * 执行用时：392 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：71 / 71
   *
   * @param n
   * @return
   */
  def findNthDigit(n: Int): Int = {
    SolutionOffer44.findNthDigit(n)
  }
}
