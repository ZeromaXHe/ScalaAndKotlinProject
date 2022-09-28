package com.zerox.scala.from1701to1750

import scala.io.StdIn

/**
 * @author zhuxi
 * @since 2022/9/28 10:36
 * @note
 * 1734B. Bright, Nice, Brilliant | 难度：800
 */
object Solution1734B {
  /**
   * AC 405 ms
   * 直接 print 不使用 StringBuilder 的话会超时
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()
    val sb = new StringBuilder
    for (_ <- 0 until t) {
      val n = StdIn.readInt()
      sb.clear()
      sb.append("1")
      println(sb)
      if (n > 1) {
        sb.append(" 1")
        println(sb)
      }
      for (i <- 2 until n) {
        sb.delete(sb.length - 2, sb.length)
        sb.append(" 0 1")
        println(sb)
      }
    }
  }
}
