package com.zerox.from801to1000

/**
 * 896. 单调数列 | 难度：简单 | 标签：数组
 * 如果数组是单调递增或单调递减的，那么它是 单调 的。
 *
 * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。 如果对于所有 i <= j，nums[i]> = nums[j]，那么数组 nums 是单调递减的。
 *
 * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [6,5,4,4]
 * 输出：true
 *
 * 示例 3：
 * 输入：nums = [1,3,2]
 * 输出：false
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 15:55
 */
object Solution896 {
    /**
     * 执行用时：544 ms, 在所有 Kotlin 提交中击败了 75.00% 的用户
     * 内存消耗：52.9 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：371 / 371
     */
    fun isMonotonic(nums: IntArray): Boolean {
        var incr = false
        var decr = false
        return !nums.indices.any {
            if (it != 0) {
                if (nums[it] > nums[it - 1]) incr = true
                else if (nums[it] < nums[it - 1]) decr = true
            }
            incr && decr
        }
    }
}