package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/8/29 14:59
 * @note
 * 1223. 掷骰子模拟 | 难度：困难 | 标签：数组、动态规划
 * 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
 *
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
 *
 * 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
 *
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
 *
 * 示例 1：
 * 输入：n = 2, rollMax = [1,1,2,2,2,3]
 * 输出：34
 * 解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
 *
 * 示例 2：
 * 输入：n = 2, rollMax = [1,1,1,1,1,1]
 * 输出：30
 *
 * 示例 3：
 * 输入：n = 3, rollMax = [1,1,1,2,2,3]
 * 输出：181
 *
 * 提示：
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dice-roll-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1223 {
  def main(args: Array[String]): Unit = {
    println(dieSimulator(4, Array(2, 1, 1, 3, 3, 2)))
  }

  val MOD: Int = (1e9 + 7).toInt

  /**
   * 执行用时：544 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：32 / 32
   *
   * @param n
   * @param rollMax
   * @return
   */
  def dieSimulator(n: Int, rollMax: Array[Int]): Int = {
    val dp = Array.ofDim[Int](n, 6)
    for (i <- rollMax.indices) {
      dp(0)(i) = 1
    }
    for (i <- 1 until n) {
      val pre = dp(i - 1).reduce((a, b) => (a + b) % MOD)
      for (j <- rollMax.indices) {
        dp(i)(j) = pre
        if (i == rollMax(j)) dp(i)(j) -= 1
        else if (i - rollMax(j) > 0) {
          for (k <- rollMax.indices if j != k) dp(i)(j) = (dp(i)(j) - dp(i - rollMax(j) - 1)(k) + MOD) % MOD
        }
      }
    }
    var sum = 0
    for (i <- rollMax.indices) {
      sum = (sum + dp(n - 1)(i)) % MOD
    }
    sum
  }
}
