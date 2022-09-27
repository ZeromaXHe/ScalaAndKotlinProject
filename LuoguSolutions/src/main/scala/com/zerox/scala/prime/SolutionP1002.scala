package com.zerox.scala.prime

import scala.io.StdIn

/**
 * @author zhuxi
 * @since 2022/9/27 18:02
 * @note
 * P1002 [NOIP2002 普及组] 过河卒 | 难度：普及-
 */
object SolutionP1002 {
  /**
   * AC 大概 187ms 左右
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val Array(bx, by, cx, cy) = StdIn.readLine().split(" ").map(_.toInt)
    val set = Set((cx + 1, cy + 2), (cx + 1, cy - 2), (cx - 1, cy - 2), (cx - 1, cy + 2),
      (cx + 2, cy + 1), (cx - 2, cy + 1), (cx - 2, cy - 1), (cx + 2, cy - 1), (cx, cy))
    if (set.contains((0, 0))) {
      println(0)
      // 用 return 会编译报错，有点莫名其妙
    } else {
      // Int 溢出有点恶心
      val dp = Array.ofDim[Long](bx + 1, by + 1)
      dp(0)(0) = 1
      for (i <- 0 to bx; j <- 0 to by if !set.contains((i, j))) {
        if (i > 0) dp(i)(j) += dp(i - 1)(j)
        if (j > 0) dp(i)(j) += dp(i)(j - 1)
      }
      println(dp(bx)(by))
    }
  }
}
