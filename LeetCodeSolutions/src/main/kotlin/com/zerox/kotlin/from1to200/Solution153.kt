package com.zerox.kotlin.from1to200

/**
 * 153. 寻找旋转排序数组中的最小值 | 难度：中等 | 标签：数组、二分查找
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 17:07
 */
object Solution153 {
    /**
     * 执行用时：164 ms, 在所有 Kotlin 提交中击败了 84.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Kotlin 提交中击败了 72.00% 的用户
     * 通过测试用例：150 / 150
     */
    fun findMin(nums: IntArray): Int {
        var l = 0
        var r = nums.size - 1
        var min = nums[0]
        while (l < r) {
            val mid = (l + r) / 2
            if (l == mid) {
                min = min.coerceAtMost(nums[l])
                l = r
            } else if (nums[l] < nums[mid]) {
                // l 到 mid 是递增的
                if (nums[r] < nums[l]) {
                    min = min.coerceAtMost(nums[r])
                    l = mid + 1
                } else {
                    min = min.coerceAtMost(nums[l])
                    r = mid - 1
                }
            } else {
                // mid 到 right 是递增的
                min = min.coerceAtMost(nums[mid])
                r = mid - 1
            }
        }
        return min.coerceAtMost(nums[l])
    }
}