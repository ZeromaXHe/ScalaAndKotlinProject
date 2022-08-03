package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/8/3 18:21
 * @note
 * 9. 回文数 | 难度：简单 | 标签：数学
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 *
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 提示：
 * -231 <= x <= 231 - 1
 *  
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution9 {
  def main(args: Array[String]): Unit = {
    println(isPalindrome(121))
  }

  /**
   * 执行用时：492 ms, 在所有 Scala 提交中击败了 93.88% 的用户
   * 内存消耗：51.6 MB, 在所有 Scala 提交中击败了 65.31% 的用户
   * 通过测试用例：11510 / 11510
   *
   * @param x
   * @return
   */
  def isPalindrome(x: Int): Boolean = {
    x >= 0 && x == reverse(x)
  }

  private def reverse(x: Int): Int = {
    var result = 0
    var vx = x
    while (vx > 0) {
      if (result > Int.MaxValue / 10) return -1
      result *= 10
      result += vx % 10
      vx /= 10
    }
    result
  }
}
