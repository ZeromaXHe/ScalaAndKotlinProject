package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/8/24 16:40
 * @note
 * 322. 零钱兑换 | 难度：中等 | 标签：广度优先搜索、数组、动态规划
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution322 {
  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 54.55% 的用户
   * 内存消耗：53.2 MB, 在所有 Scala 提交中击败了 36.36% 的用户
   * 通过测试用例：189 / 189
   *
   * @param coins
   * @param amount
   * @return
   */
  def coinChange(coins: Array[Int], amount: Int): Int = {
    val dp = Array.fill(amount + 1)(amount + 1)
    dp(0) = 0
    for (i <- 1 to amount;
         j <- coins if j <= i && dp(i - j) + 1 < dp(i)) {
      dp(i) = dp(i - j) + 1
    }
    if (dp(amount) == amount + 1) -1 else dp(amount)
  }
}
