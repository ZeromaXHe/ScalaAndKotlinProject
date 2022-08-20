package com.zerox.kotlin.from201to400

/**
 * 213. 打家劫舍 II | 难度：中等 | 标签：数组、动态规划
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/20 11:51
 */
object Solution213 {
    /**
     * 执行用时：140 ms, 在所有 Kotlin 提交中击败了 92.00% 的用户
     * 内存消耗：32.8 MB, 在所有 Kotlin 提交中击败了 68.00% 的用户
     * 通过测试用例：75 / 75
     */
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        val dp1 = IntArray(2)
        val dp2 = IntArray(2)
        for (i in 1 until nums.size) {
            dp1[i % 2] = (dp1[i % 2] + nums[i]).coerceAtLeast(dp1[(i + 1) % 2])
            dp2[i % 2] = (dp2[i % 2] + nums[nums.size - 1 - i]).coerceAtLeast(dp2[(i + 1) % 2])
        }
        return dp1[0].coerceAtLeast(dp1[1]).coerceAtLeast(dp2[0]).coerceAtLeast(dp2[1])
        // 1.3 没有 maxOf 的多参版本
//        return maxOf(dp1[0], dp1[1], dp2[0], dp2[1])
    }
}