package com.zerox.scala.from1701to1750

import scala.io.StdIn

/**
 * @author zhuxi
 * @since 2022/9/28 11:11
 * @note
 * 1734C. Removing Smallest Multiples | 难度：1200
 */
object Solution1734C {
  /**
   * AC 920 ms
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()
    for (_ <- 0 until t) {
      val n = StdIn.readInt()
      val visit = new Array[Boolean](n + 1)
      val str = StdIn.readLine()
      var res = 0L
      for (i <- 1 to n) {
        var break = false
        for (j <- i to n by i if !break) {
          if (str(j - 1) == '1') break = true
          else if (!visit(j)) {
            visit(j) = true
            res += i
          }
        }
      }
      println(res)
    }
  }
}
