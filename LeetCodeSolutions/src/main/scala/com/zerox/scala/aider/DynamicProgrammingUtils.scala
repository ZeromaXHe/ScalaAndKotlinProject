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
   *
   * @param cache            用于记忆缓存的哈希表
   * @param key              要查询的键
   * @param generate         没有缓存时的生成值逻辑
   * @param noCacheCondition 不需要缓存的条件
   * @param noCacheGenerate  不需要缓存时的生成值逻辑
   * @tparam K 键类型
   * @tparam V 值类型
   * @return
   */
  def cachedHash[K, V](cache: scala.collection.mutable.HashMap[K, V], key: K, generate: K => V,
                       noCacheCondition: K => Boolean = (_: K) => false, noCacheGenerate: K => V = null): V = {
    if (noCacheCondition(key)) noCacheGenerate(key)
    else cache.getOrElseUpdate(key, generate(key))
  }

  def cachedArray[T](cache: Array[T], i: Int, generate: Int => T, arrDefault: T,
                     noCacheCondition: Int => Boolean = _ => false,
                     noCacheGenerate: Int => T = null): T = {
    if (noCacheCondition(i)) noCacheGenerate(i)
    else {
      if (cache(i) == arrDefault) cache(i) = generate(i)
      cache(i)
    }
  }

  def cachedArray2D[T](cache: Array[Array[T]], i: Int, j: Int, generate: (Int, Int) => T, arrDefault: T,
                       noCacheCondition: (Int, Int) => Boolean = (_, _) => false,
                       noCacheGenerate: (Int, Int) => T = null): T = {
    if (noCacheCondition(i, j)) noCacheGenerate(i, j)
    else {
      if (cache(i)(j) == arrDefault) cache(i)(j) = generate(i, j)
      cache(i)(j)
    }
  }
}
