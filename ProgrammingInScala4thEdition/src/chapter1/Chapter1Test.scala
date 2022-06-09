package chapter1

import java.math.BigInteger

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 11:02
 * @Description: 第1章 一门可伸缩的语言
 * @ModifiedBy: zhuxi
 */
object Chapter1Test {

  def main(args: Array[String]): Unit = {
    test1_1()
    test1_3()
  }

  /**
   * 1.1 一门按需伸缩的语言
   */
  def test1_1() = {
    var capital = Map("US" -> "Washington", "France" -> "Paris")
    capital += ("Japan" -> "Tokyo")
    println(capital("France"))

    // “培育新类型” BigInt 示例代码
    println(factorial(30))
  }

  /**
   * 很多应用程序都需要一种不会溢出（overflow）或者说“从头开始”（wrap-around）的整数。
   * Scala 正好就定义了一个类型 scala.math.BigInt。
   *
   * @param x
   * @return
   */
  def factorial(x: BigInt): BigInt =
    if (x == 0) 1 else x * factorial(x - 1)

  /**
   * 当然了，也可以直接使用 Java 的这个类。
   * 不过用起来并不会那么舒服，因为尽管 Java 也允许创建新的类型，这些类型用起来并不会给人原生支持的体验。
   *
   * @param x
   * @return
   */
  def factorial(x: BigInteger): BigInteger =
    if (x == BigInteger.ZERO)
      BigInteger.ONE
    else
      x.multiply(factorial(x.subtract(BigInteger.ONE)))

  /**
   * 1.3 为什么要用 Scala
   */
  def test1_3(): Unit = {
    // “Scala 是高级的”示例代码
    val name = "Abc";
    val nameHasUpperCase = name.exists(_.isUpper)
    println(nameHasUpperCase)
  }
}
