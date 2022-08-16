package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/8/16 11:35
 * @note
 * 43. 字符串相乘 | 难度：中等 | 标签：数学、字符串、模拟
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution43 {
  /**
   * 执行用时：520 ms, 在所有 Scala 提交中击败了 76.92% 的用户
   * 内存消耗：52.7 MB, 在所有 Scala 提交中击败了 84.62% 的用户
   * 通过测试用例：311 / 311
   *
   * @param num1
   * @param num2
   * @return
   */
  def multiply(num1: String, num2: String): String = {
    if ("0".equals(num1) || "0".equals(num2)) return "0"
    val result = new Array[Int](num1.length + num2.length)
    for (i <- num1.indices; j <- num2.indices) {
      val prod = (num1(i) - '0') * (num2(j) - '0')
      add(result, i + j + 1, prod % 10)
      if (prod >= 10) add(result, i + j, prod / 10)
    }

    val sb = new StringBuilder
    for (i <- result.indices) {
      if (i != 0 || result(i) != 0) sb.append(result(i))
    }
    sb.toString()
  }

  private def add(result: Array[Int], idx: Int, add: Int) = {
    var i = idx
    result(i) += add
    while (result(i) >= 10) {
      result(i - 1) += result(i) / 10
      result(i) %= 10
      i -= 1
    }
  }
}
