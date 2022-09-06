package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 14:29
 * @note
 * 动态规划工具类
 */
object DynamicProgrammingUtils {
  def dpOneDimension(n: Int, inits: List[(Int, Int)],
                     trans: (Array[Int], Int) => Int, from: Int = 1): Array[Int] = {
    val dp = new Array[Int](n + 1)
    for ((i, elem) <- inits) dp(i) = elem
    for (i <- from to n) dp(i) = trans(dp, i)
    dp
  }

  /**
   * 暂时还比较难用，后续慢慢优化参数设计
   *
   * @param m
   * @param n
   * @param inits
   * @param optExec
   * @param trans
   * @param from
   * @return
   */
  def dpTwoDimension(m: Int, n: Int, inits: List[(Int, Int, Int)],
                     optExec: Int => Array[Int],
                     trans: (Array[Array[Int]], Int, Int, Array[Int]) => Int,
                     from: Int = 1): Array[Array[Int]] = {
    val dp = Array.ofDim[Int](m + 1, n + 1)
    for ((i, j, elem) <- inits) dp(i)(j) = elem
    for (i <- from to m) {
      val optExecRes = optExec(i)
      for (j <- from to n) {
        dp(i)(j) = trans(dp, i, j, optExecRes)
      }
    }
    dp
  }

  /**
   * 记忆化
   * 其实可以不套这个模板，只是通过本模板写一下 Scala 中的 HashMap 这种默认支持的写法
   *
   * @param cache
   * @param i
   * @param generate
   * @return
   */
  def cached(cache: scala.collection.mutable.HashMap[Int, Int], i: Int, generate: Int => Int): Int = {
    cache.getOrElseUpdate(i, generate(i))
  }
}
