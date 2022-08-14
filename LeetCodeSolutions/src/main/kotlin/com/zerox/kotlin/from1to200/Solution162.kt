package com.zerox.kotlin.from1to200

/**
 * 162. 寻找峰值 | 难度：中等 | 标签：数组、二分查找
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 17:25
 */
object Solution162 {
    /**
     * 执行用时：172 ms, 在所有 Kotlin 提交中击败了 69.57% 的用户
     * 内存消耗：35.7 MB, 在所有 Kotlin 提交中击败了 65.22% 的用户
     * 通过测试用例：63 / 63
     */
    fun findPeakElement(nums: IntArray): Int {
        if (nums.size == 1) return 0
        var l = 0
        var r = nums.size - 1
        while (l < r) {
            val mid = (l + r) / 2
            if (mid == 0 || nums[mid - 1] < nums[mid]) {
                if (nums[mid] < nums[mid + 1]) l = mid + 1
                else return mid
            } else {
                r = mid - 1
            }
        }
        return l
    }
}