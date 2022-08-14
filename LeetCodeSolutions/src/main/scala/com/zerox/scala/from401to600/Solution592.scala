package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/7/27 9:44
 * @note
 * 592. 分数加减运算 | 难度：中等 | 标签：数学、字符串、模拟
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 *
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 *
 * 示例 1:
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 *
 *  示例 2:
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 *
 * 示例 3:
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 *
 * 提示:
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution592 {
  def main(args: Array[String]): Unit = {
    val array = "-1/2+1/2+1/3".split("[+-]").filter(_ != "").map(_.split("/").map(_.toInt))
    val str = array.map(_.mkString("Array(", ", ", ")")).mkString("Array(", ", ", ")")
    println(str)

    println(fractionAddition("-1/2+1/2")) // 0/1
    println(fractionAddition("-1/2+1/2+1/3")) // 1/3
    println(fractionAddition("1/3-1/2")) // -1/6
  }

  /**
   * 执行用时：524 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：105 / 105
   *
   * @param expression
   * @return
   */
  def fractionAddition_notConciseEnough(expression: String): String = {
    val signs = (if (expression(0) == '-') "" else "+") + expression.filter(c => c == '+' || c == '-')
    val rationals = expression.split("[+-]").filter(_ != "").map(_.split("/").map(_.toInt)).map(arr => new Rational(arr(0), arr(1)))
    (signs zip rationals).scanLeft(new Rational(0))((preSum: Rational, tuple) => {
      tuple._1 match {
        case '+' => preSum + tuple._2
        case '-' => preSum - tuple._2
      }
    }).last.toString
  }

  class Rational(n: Int, d: Int) {
    require(d != 0)

    private val g = gcd(n.abs, d.abs)
    val numer: Int = n / g
    val denom: Int = d / g

    def this(n: Int) = this(n, 1)

    def +(that: Rational): Rational =
      new Rational(
        numer * that.denom + that.numer * denom,
        denom * that.denom
      )

    def -(that: Rational): Rational =
      new Rational(
        numer * that.denom - that.numer * denom,
        denom * that.denom
      )

    override def toString: String = s"$numer/$denom"

    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  }

  /**
   * 执行用时：476 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：105 / 105
   *
   * @param expression
   * @return
   */
  def fractionAddition(expression: String): String = {
    expression.replace("-", "+-").split("\\+").filter(_ != "")
      .map(_.split("/").map(_.toInt) match {
        case Array(n, d) => new Rational2(n, d)
      }).reduce((sum, cur) => sum + cur).toString
  }

  class Rational2(n: Int, d: Int) {
    private val g = gcd(n.abs, d.abs)
    val numer: Int = n / g
    val denom: Int = d / g

    def +(that: Rational2): Rational2 =
      new Rational2(
        numer * that.denom + that.numer * denom,
        denom * that.denom
      )

    override def toString: String = s"$numer/$denom"

    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  }
}
