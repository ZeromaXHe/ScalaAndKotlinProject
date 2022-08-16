package com.zerox.kotlin.from201to400

/**
 * 309. 最佳买卖股票时机含冷冻期 | 难度：中等 | 标签：数组、动态规划
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 14:22
 */
object Solution309 {
    /**
     * 执行用时：168 ms, 在所有 Kotlin 提交中击败了 70.00% 的用户
     * 内存消耗：34.4 MB, 在所有 Kotlin 提交中击败了 60.00% 的用户
     * 通过测试用例：210 / 210
     */
    fun maxProfit(prices: IntArray): Int {
        // dp[i][0]: 手上持有股票的最大收益
        // dp[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // dp[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        val dp = Array(prices.size) { IntArray(3) }
        dp[0][0] = -prices[0]
        for (i in 1 until prices.size) {
            dp[i][0] = dp[i - 1][0].coerceAtLeast(dp[i - 1][2] - prices[i])
            dp[i][1] = dp[i - 1][0] + prices[i]
            dp[i][2] = dp[i - 1][1].coerceAtLeast(dp[i - 1][2])
        }
        return dp[prices.size - 1][1].coerceAtLeast(dp[prices.size - 1][2])
    }
}