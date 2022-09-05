package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 14:29
 * @note
 * 动态规划工具类
 */
object DynamicProgrammingUtils {
  def dpOneDimension(n: Int, inits: List[(Int, Int)], coords: List[Int => Int],
                     trans: (Array[Int], List[Int => Int]) => Int, from: Int): Array[Int] = {
    val dp = new Array[Int](n)
    for ((i, elem) <- inits) dp(i) = elem
    for (i <- from until n) dp(i) = trans(dp, coords)
    dp
  }
}
