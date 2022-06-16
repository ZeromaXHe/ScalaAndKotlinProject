package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 14:52
 * @note
 * 20.5 初始化抽象的 val
 */
trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  override def toString: String = s"$numer/$denom"
}
