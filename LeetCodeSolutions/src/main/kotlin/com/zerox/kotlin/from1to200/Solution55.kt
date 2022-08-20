package com.zerox.kotlin.from1to200

/**
 * 55. 跳跃游戏 | 难度：中等 | 标签：贪心、数组、动态规划
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/20 11:58
 */
object Solution55 {
    /**
     * 执行用时：312 ms, 在所有 Kotlin 提交中击败了 60.29% 的用户
     * 内存消耗：39.9 MB, 在所有 Kotlin 提交中击败了 23.53% 的用户
     * 通过测试用例：170 / 170
     */
    fun canJump(nums: IntArray): Boolean {
        var max = 0
        for (i in nums.indices) {
            if (i > max) return false
            if (nums[i] + i > max) max = nums[i] + i
        }
        return true
    }
}