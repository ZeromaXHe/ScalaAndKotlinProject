package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/21 11:56
 * @note
 * 224. 基本计算器 | 难度：困难 | 标签：栈、递归、数学、字符串
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 *
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution224 {
  def main(args: Array[String]): Unit = {
    println(calculate("(1+(4+5+2)-3)+(6+8)")) // 23
  }

  def calculate(s: String): Int = {
    calculate(s, 0)._1
  }

  /**
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：44 / 44
   *
   * @param s
   * @param from
   * @return
   */
  def calculate(s: String, from: Int): (Int, Int) = {
    var sum = 0
    var sign = 1
    var pre = 0
    var i = from
    while (i < s.length) {
      if (s(i) == ' ') i += 1
      else if (s(i) == ')') {
        return (sum + pre * sign, i + 1)
      } else if (s(i) == '(') {
        val (calc, to) = calculate(s, i + 1)
        sum += calc * sign
        i = to
      } else if (Character.isDigit(s(i))) {
        pre *= 10
        pre += s(i) - '0'
        i += 1
      } else {
        sum += pre * sign
        sign = if (s(i) == '+') 1 else -1
        pre = 0
        i += 1
      }
    }
    (sum + pre * sign, i)
  }
}
