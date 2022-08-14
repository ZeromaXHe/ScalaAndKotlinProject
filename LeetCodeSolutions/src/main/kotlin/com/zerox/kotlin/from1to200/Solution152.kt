package com.zerox.kotlin.from1to200

/**
 * 152. 乘积最大子数组 | 难度：中等 | 标签：数组、动态规划
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 子数组 是数组的连续子序列。
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 17:08
 */
object Solution152 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(maxProduct(intArrayOf(7, -2, -4)))
    }

    /**
     * 执行用时：216 ms, 在所有 Kotlin 提交中击败了 38.71% 的用户
     * 内存消耗：34.6 MB, 在所有 Kotlin 提交中击败了 87.10% 的用户
     * 通过测试用例：188 / 188
     */
    fun maxProduct(nums: IntArray): Int {
        var max = Int.MIN_VALUE
        var pos = 0
        var neg = 0
        for (num in nums) {
            if (num == 0) {
                pos = 0
                neg = 0
                if (max < 0) max = 0
            } else if (num > 0) {
                if (pos == 0) pos = num
                else pos *= num
                neg *= num
            } else {
                pos = (neg * num).also { if (pos == 0) neg = num else neg = pos * num }
            }
            if (pos > max && pos > 0) max = pos
            if (neg > max && neg < 0) max = neg
        }
        return max
    }
}