package com.zerox.from201to400

import com.zerox.offer.SolutionOffer43

/**
 * @author zhuxi
 * @since 2022/7/9 11:28
 * @note
 * 233. 数字 1 的个数 | 难度：困难 | 标签：递归、数学、动态规划
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 提示：
 * 0 <= n <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution233 {
  /**
   * 执行用时：472 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：38 / 38
   *
   * @param n
   * @return
   */
  def countDigitOne(n: Int): Int = {
    SolutionOffer43.countDigitOne(n)
  }
}
