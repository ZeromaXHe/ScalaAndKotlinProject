package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/8/13 10:37
 * @note
 * 67. 二进制求和 | 难度：简单 | 标签：位运算、数学、字符串、模拟
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution67 {
  /**
   * 执行用时：484 ms, 在所有 Scala 提交中击败了 75.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 75.00% 的用户
   * 通过测试用例：294 / 294
   *
   * @param a
   * @param b
   * @return
   */
  def addBinary(a: String, b: String): String = {
    var pa = a.length - 1
    var pb = b.length - 1
    var carry = 0
    val sb = new StringBuilder
    while (pa >= 0 && pb >= 0) {
      val sum = (a(pa) - '0') + (b(pb) - '0') + carry
      carry = sum >> 1
      sb.append(sum & 1)
      pa -= 1
      pb -= 1
    }
    while (pa >= 0) {
      val sum = (a(pa) - '0') + carry
      carry = sum >> 1
      sb.append(sum & 1)
      pa -= 1
    }
    while (pb >= 0) {
      val sum = (b(pb) - '0') + carry
      carry = sum >> 1
      sb.append(sum & 1)
      pb -= 1
    }
    if (carry > 0) sb.append(carry)
    sb.reverse.toString()
  }
}
