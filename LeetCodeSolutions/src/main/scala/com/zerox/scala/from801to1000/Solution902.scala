package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/18 16:00
 * @note
 * 902. 最大为 N 的数字组合 | 难度：困难
 */
object Solution902 {
  /**
   * 参考题解做的
   *
   * 时间 560 ms 击败 100%
   * 内存 52.3 MB 击败 100%
   *
   * @param digits
   * @param n
   * @return
   */
  def atMostNGivenDigitSet(digits: Array[String], n: Int): Int = {
    val s = n.toString
    val k = s.length
    // 我们用 dp[i][0] 表示由 digits 构成且小于 n 的前 i 位的数字的个数，
    // dp[i][1] 表示由 digits 构成且等于 n 的前 i 位的数字的个数，可知 dp[i][1] 的取值只能为 0 和 1。
    val dp = Array.ofDim[Int](k + 1, 2)
    dp(0)(1) = 1
    for (i <- 1 to k) {
      var break = false
      for (j <- digits.indices if !break) {
        if (digits(j)(0) == s(i - 1)) dp(i)(1) = dp(i - 1)(1)
        else if (digits(j)(0) < s(i - 1)) dp(i)(0) += dp(i - 1)(1)
        else break = true
      }
      if (i > 1) dp(i)(0) += digits.length + dp(i - 1)(0) * digits.length
    }
    dp(k)(0) + dp(k)(1)
  }
}
