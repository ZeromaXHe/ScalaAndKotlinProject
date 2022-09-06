package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/6 15:42
 * @note
 * 前缀和（一维）
 * @param arr
 * @param mod 结果取余的除数
 */
class PreSumArray(arr: Array[Int], mod: Int = Int.MaxValue) {
  private val preSum = new Array[Long](arr.length + 1)

  for (i <- arr.indices) {
    preSum(i + 1) = preSum(i) + arr(i)
    if (mod < Int.MaxValue) preSum(i + 1) %= mod
  }

  def apply(from: Int, to: Int): Long = {
    val res = preSum(to) - preSum(from)
    if (mod == Int.MaxValue || res >= 0) res
    else res + mod
  }
}
