package com.zerox.kotlin.from1401to1600

/**
 * 1567. 乘积为正数的最长子数组长度 | 难度：中等 | 标签：贪心、数组、动态规划
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 *
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 *
 * 请你返回乘积为正数的最长子数组长度。
 *
 * 示例  1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 *
 * 示例 2：
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 17:40
 */
object Solution1567 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(getMaxLen(intArrayOf(-1, -2, -3, 0, 1)))
    }

    /**
     * 执行用时：460 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：62.3 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：112 / 112
     */
    fun getMaxLen(nums: IntArray): Int {
        var max = 0
        var pos = 0
        var neg = 0
        for (num in nums) {
            if (num == 0) {
                pos = 0
                neg = 0
            } else if (num > 0) {
                pos += 1
                if (neg > 0) neg += 1
            } else {
                pos = (if (neg > 0) neg + 1 else 0).also {
                    neg = if (pos == 0) 1 else (pos + 1)
                }
            }
            if (pos > max) max = pos
        }
        return max
    }
}