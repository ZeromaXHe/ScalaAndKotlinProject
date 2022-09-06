package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 10:36
 * @note
 * 数学相关工具类
 */
object MathUtils {
  /**
   * 使用 mod 对每次计算进行取余的求和
   *
   * @param s
   * @param mod
   * @return
   */
  def sumWithMod(s: Seq[Int], mod: Int): Int = {
    var sum = 0
    for (i <- s) sum = (sum + i) % mod
    sum
  }

  /**
   * 阶乘
   *
   * @param n 必须在 [0, 25] 之间
   * @return
   */
  def factorial(n: Int): Long = {
    if (n == 0) return 1
    (1L to n).product
  }

  /**
   * 使用 mod 对每次计算进行取余的阶乘
   *
   * @param n 必须大于等于 0
   * @param mod
   * @return
   */
  def factorialWithMod(n: Int, mod: Int): Int = {
    if (n == 0) return 1
    var product = 1L
    for (i <- 2 to n) product = (product * i) % mod
    product.toInt
  }

  /**
   * 求组合数 C(m, n)
   *
   * @param m
   * @param n
   * @return
   */
  def combination(m: Int, n: Int): Long = {
    if (m > n / 2) return combination(n - m, n)
    var res = 1L
    for (i <- 1 to m) {
      res *= n + 1 - i
      res /= i
    }
    res
  }

  /**
   * 求两个数的最大公约数
   *
   * @param a
   * @param b
   * @return
   */
  @scala.annotation.tailrec
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  @scala.annotation.tailrec
  def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)

  /**
   * 求两个数的最小公倍数
   *
   * @param a
   * @param b
   * @return
   */
  def lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

  /**
   * 求一组数的最大公约数
   *
   * @param arr
   * @return
   */
  def gcd(arr: Array[Int]): Int = arr.reduce((res, next) => gcd(res, next))
}
