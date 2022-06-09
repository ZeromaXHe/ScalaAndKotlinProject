package chapter6

import scala.language.implicitConversions

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 15:50
 * @Description: 第6章 函数式对象
 * @ModifiedBy: zhuxi
 */
object Chapter6Test {
  def main(args: Array[String]): Unit = {
    val oneHalf = new Rational(1, 2)
    println(oneHalf)
    println(oneHalf.numer)
    println(oneHalf.denom)
    val twoThirds = new Rational(2, 3)
    println(oneHalf + twoThirds)

    println(twoThirds * twoThirds)
    println(twoThirds * 2)

    // 可以创建一个隐式转换（implicit conversion），在需要时自动将整数转换成有理数
    implicit def intToRational(x: Int): Rational = new Rational(x)

    println(2 * twoThirds)
  }
}
