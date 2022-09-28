package com.zerox.scala.from1701to1750

import scala.io.StdIn

/**
 * @author zhuxi
 * @since 2022/9/28 10:08
 * @note
 * 1734A. Select Three Sticks | 难度：800
 */
object Solution1734A {
  /**
   * AC 420 ms
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()
    for (_ <- 0 until t) {
      val _ = StdIn.readInt()
      val arr = StdIn.readLine().split(" ").map(_.toInt).sorted
      var minimum = Int.MaxValue
      for (j <- 0 until arr.length - 2) {
        minimum = minimum min (arr(j + 2) + arr(j + 1) - 2 * arr(j)) min
          (arr(j + 2) * 2 - arr(j + 1) - arr(j)) min (arr(j + 2) - arr(j))
      }
      println(minimum)
    }
  }
}
