package com.zerox.from1to200

/**
 * 121. 买卖股票的最佳时机 | 难度：简单 | 标签：数组、动态规划
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 16:55
 */
object Solution121 {
    /**
     * 执行用时：520 ms, 在所有 Kotlin 提交中击败了 54.44% 的用户
     * 内存消耗：55.7 MB, 在所有 Kotlin 提交中击败了 75.56% 的用户
     * 通过测试用例：211 / 211
     */
    fun maxProfit(prices: IntArray): Int {
        var result = 0
        var min = prices[0]
        for (i in 1 until prices.size) {
            if (prices[i] < min) min = prices[i]
            else if (prices[i] - min > result) result = prices[i] - min
        }
        return result
    }
}