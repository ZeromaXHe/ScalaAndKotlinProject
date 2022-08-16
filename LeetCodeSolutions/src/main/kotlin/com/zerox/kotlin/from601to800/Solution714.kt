package com.zerox.kotlin.from601to800

/**
 * 714. 买卖股票的最佳时机含手续费 | 难度：中等 | 标签：贪心、数组、动态规划
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 * 提示：
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 14:55
 */
object Solution714 {
    /**
     * 执行用时：392 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 内存消耗：45.4 MB, 在所有 Kotlin 提交中击败了 25.00% 的用户
     * 通过测试用例：44 / 44
     */
    fun maxProfit(prices: IntArray, fee: Int): Int {
        // dp[i][0]: 手上持有股票的最大收益
        // dp[i][1]: 手上不持有股票最大收益
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = -prices[0] - fee
        for (i in 1 until prices.size) {
            dp[i][0] = dp[i - 1][0].coerceAtLeast(dp[i - 1][1] - prices[i] - fee)
            dp[i][1] = dp[i - 1][1].coerceAtLeast(dp[i - 1][0] + prices[i])
        }
        return dp[prices.size - 1][1]
    }

    /**
     * 执行用时：420 ms, 在所有 Kotlin 提交中击败了 25.00% 的用户
     * 内存消耗：46.5 MB, 在所有 Kotlin 提交中击败了 25.00% 的用户
     * 通过测试用例：44 / 44
     */
    fun maxProfit_simple(prices: IntArray, fee: Int): Int {
        var sell = 0
        var hold = -prices[0] - fee
        for (i in 1 until prices.size) {
            hold = (hold.coerceAtLeast(sell - prices[i] - fee)).also {
                sell = sell.coerceAtLeast(hold + prices[i])
            }
        }
        return sell
    }
}