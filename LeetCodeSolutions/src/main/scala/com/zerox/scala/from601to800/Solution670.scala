package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/9/13 9:52
 * @note
 * 670. 最大交换 | 难度：中等 | 标签：贪心、数学
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 * 给定数字的范围是 [0, 108]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution670 {
  /**
   * 执行用时：448 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：111 / 111
   *
   * @param num
   * @return
   */
  def maximumSwap(num: Int): Int = {
    var max = num % 10
    var maxMulti = 1
    var multi = 10
    var r1 = -1
    var m1 = -1
    var r2 = -1
    var m2 = -1
    while (num / multi > 0) {
      val next = num / multi % 10
      if (next < max) {
        r1 = next
        m1 = multi
        r2 = max
        m2 = maxMulti
      } else if (next > max) {
        max = next
        maxMulti = multi
      }
      multi *= 10
    }
    if (r1 == -1) num else num + (r2 - r1) * m1 + (r1 - r2) * m2
  }
}
