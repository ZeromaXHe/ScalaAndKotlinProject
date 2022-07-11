package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/4 16:03
 * @note
 * 2318. 不同骰子序列的数目 | 难度：困难 | 标签：记忆化搜索、动态规划
 * 给你一个整数 n 。你需要掷一个 6 面的骰子 n 次。请你在满足以下要求的前提下，求出 不同 骰子序列的数目：
 *
 * 序列中任意 相邻 数字的 最大公约数 为 1 。
 * 序列中 相等 的值之间，至少有 2 个其他值的数字。正式地，如果第 i 次掷骰子的值 等于 第 j 次的值，那么 abs(i - j) > 2 。
 * 请你返回不同序列的 总数目 。由于答案可能很大，请你将答案对 109 + 7 取余 后返回。
 *
 * 如果两个序列中至少有一个元素不同，那么它们被视为不同的序列。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：184
 * 解释：一些可行的序列为 (1, 2, 3, 4) ，(6, 1, 2, 3) ，(1, 2, 3, 1) 等等。
 * 一些不可行的序列为 (1, 2, 1, 3) ，(1, 2, 3, 6) 。
 * (1, 2, 1, 3) 是不可行的，因为第一个和第三个骰子值相等且 abs(1 - 3) = 2 （下标从 1 开始表示）。
 * (1, 2, 3, 6) i是不可行的，因为 3 和 6 的最大公约数是 3 。
 * 总共有 184 个不同的可行序列，所以我们返回 184 。
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：22
 * 解释：一些可行的序列为 (1, 2) ，(2, 1) ，(3, 2) 。
 * 一些不可行的序列为 (3, 6) ，(2, 4) ，因为最大公约数不为 1 。
 * 总共有 22 个不同的可行序列，所以我们返回 22 。
 *
 * 提示：
 * 1 <= n <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-distinct-roll-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2318 {
  val MOD = (1e9 + 7).toInt
  val MX = 1e4.toInt
  // Scala 初始化多维数组
  val f = Array.ofDim[Int](MX + 1, 6, 6)

  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  for (i <- 0 until 6;
       j <- 0 until 6
       if j != i && gcd(j + 1, i + 1) == 1) {
    f(2)(i)(j) = 1
  }
  for (i <- 2 until MX;
       j <- 0 until 6;
       last <- 0 until 6
       if last != j && gcd(last + 1, j + 1) == 1;
       last2 <- 0 until 6
       if last2 != j) {
    f(i + 1)(j)(last) += f(i)(last)(last2)
    f(i + 1)(j)(last) %= MOD
  }

  /**
   * 执行用时：2640 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：64 / 64
   *
   * 参考题解 https://leetcode.cn/problems/number-of-distinct-roll-sequences/solution/by-endlesscheng-tgkn/
   *
   * @param n
   * @return
   */
  def distinctSequences(n: Int): Int = {
    if (n == 1) return 6
    var ans = 0
    for (i <- 0 until 6; j <- 0 until 6) {
      ans += f(n)(i)(j)
      ans %= MOD
    }
    ans
  }
}
