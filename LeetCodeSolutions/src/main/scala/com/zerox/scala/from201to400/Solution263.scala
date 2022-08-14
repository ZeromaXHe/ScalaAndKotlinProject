package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/7/9 17:31
 * @note
 * 263. 丑数 | 难度：简单 | 标签：数学
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 *
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 * 解释：1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。
 *
 * 示例 3：
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *
 * 提示：
 * -231 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution263 {
  /**
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 66.67% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例： 1013 / 1013
   *
   * @param n
   * @return
   */
  def isUgly(n: Int): Boolean = {
    var num = n
    while (num > 1) {
      if (num % 2 == 0) num /= 2
      else if (num % 3 == 0) num /= 3
      else if (num % 5 == 0) num /= 5
      else return false
    }
    num == 1
  }
}
