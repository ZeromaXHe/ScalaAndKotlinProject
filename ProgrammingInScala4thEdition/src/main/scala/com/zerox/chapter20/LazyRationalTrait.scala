package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 15:22
 * @note
 * 20.5 初始化抽象的 val
 */
trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  override def toString: String = s"$numer/$denom"

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
