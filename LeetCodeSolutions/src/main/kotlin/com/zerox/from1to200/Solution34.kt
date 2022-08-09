package com.zerox.from1to200

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置 | 难度：中等 | 标签：数组、二分查找
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 10:12
 */
object Solution34 {
    /**
     * 执行用时：220 ms, 在所有 Kotlin 提交中击败了 46.09% 的用户
     * 内存消耗：39.5 MB, 在所有 Kotlin 提交中击败了 21.87% 的用户
     * 通过测试用例：88 / 88
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val lower = binarySearch(nums, target)
        if (lower >= nums.size || nums[lower] != target) {
            return intArrayOf(-1, -1)
        }
        val upperPlusOne = binarySearch(nums, target + 1)
        return intArrayOf(lower, upperPlusOne - 1)
    }

    fun binarySearch(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size
        while (l < r && nums[l] != target) {
            val mid = (l + r) / 2
            if (nums[mid] >= target) {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}