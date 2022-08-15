package com.zerox.kotlin.from1001to1200

/**
 * 1014. 最佳观光组合 | 难度：中等 | 标签：数组、动态规划
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 示例 1：
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 * 示例 2：
 * 输入：values = [1,2]
 * 输出：2
 *
 * 提示：
 * 2 <= values.length <= 5 * 104
 * 1 <= values[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 15:45
 */
object Solution1014 {
    /**
     * 执行用时：308 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：44.7 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：53 / 53
     */
    fun maxScoreSightseeingPair(values: IntArray): Int {
        var i = 1
        var plus = values[0]
        var maxScore = Int.MIN_VALUE
        while (i < values.size) {
            maxScore = maxOf(maxScore, plus + values[i] - i)
            plus = plus.coerceAtLeast(values[i] + i)
            i++
        }
        return maxScore
    }
}