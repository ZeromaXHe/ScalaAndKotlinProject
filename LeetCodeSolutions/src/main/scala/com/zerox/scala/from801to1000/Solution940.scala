package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/10/14 13:33
 * @note
 * 940. 不同的子序列 II | 难度：困难
 */
object Solution940 {
  /**
   * 参考题解做的
   *
   * 时间 536 ms 击败 100%
   * 内存 53.1 MB 击败 100%
   *
   * @param s
   * @return
   */
  def distinctSubseqII(s: String): Int = {
    val MOD = (1e9 + 7).toInt
    val last = Array.fill(26)(-1)
    val n = s.length
    val dp = Array.fill(n)(1)
    for (i <- 0 until n) {
      for (j <- 0 until 26 if last(j) != -1) {
        dp(i) = (dp(i) + dp(last(j))) % MOD
      }
      last(s(i) - 'a') = i
    }
    var res = 0
    for (i <- 0 until 26 if last(i) != -1) {
      res = (res + dp(last(i))) % MOD
    }
    res
  }
}
