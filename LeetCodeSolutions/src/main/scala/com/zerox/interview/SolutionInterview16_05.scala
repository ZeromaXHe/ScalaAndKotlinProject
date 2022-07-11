package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/6/30 11:45
 * @note
 * 面试题 16.05. 阶乘尾数 | 难度：简单 | 标签：数学
 * 设计一个算法，算出 n 阶乘有多少个尾随零。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/factorial-zeros-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_05 {
  /**
   * 执行用时：436 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：502 / 502
   *
   * @param n
   * @return
   */
  def trailingZeroes(n: Int): Int = {
    var div = n
    var result = 0
    while (div > 0) {
      // 往上乘的话会有溢出的问题
      div /= 5
      result += div
    }
    result
  }
}
