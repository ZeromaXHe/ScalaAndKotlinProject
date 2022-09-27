package com.zerox.scala.prime

import java.util.Scanner
import scala.io.StdIn

/**
 * @author zhuxi
 * @since 2022/9/27 17:15
 * @note
 * P1001 A+B Problem | 难度：入门
 */
object SolutionP1001 {
  /**
   * AC 大概 45ms
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val str = StdIn.readLine()
    val strs = str.split(" ")
    println(strs(0).toInt + strs(1).toInt)
  }


  /**
   * AC 大概 75ms
   * @param args
   */
  def main_javaScanner(args: Array[String]): Unit = {
    val scanner = new Scanner(System.in)
    val i1 = scanner.nextInt()
    val i2 = scanner.nextInt()
    println(i1 + i2)
  }
}
