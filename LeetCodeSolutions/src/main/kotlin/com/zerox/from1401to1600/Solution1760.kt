package com.zerox.from1401to1600

/**
 * 1760. 袋子里最少数目的球 | 难度：中等 | 标签：数组、二分查找
 * 给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。
 *
 * 你可以进行如下操作至多 maxOperations 次：
 *
 * 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。
 * 比方说，一个袋子里有 5 个球，你可以把它们分到两个新袋子里，分别有 1 个和 4 个球，或者分别有 2 个和 3 个球。
 * 你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。
 *
 * 请你返回进行上述操作后的最小开销。
 *
 * 示例 1：
 * 输入：nums = [9], maxOperations = 2
 * 输出：3
 * 解释：
 * - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
 * - 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
 * 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
 *
 * 示例 2：
 * 输入：nums = [2,4,8,2], maxOperations = 4
 * 输出：2
 * 解释：
 * - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
 * 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
 *
 * 示例 3：
 * 输入：nums = [7,17], maxOperations = 2
 * 输出：7
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= maxOperations, nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 11:50
 */
object Solution1760 {
    /**
     * 执行用时：468 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：53.4 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：58 / 58
     */
    fun minimumSize(nums: IntArray, maxOperations: Int): Int {
        var l = 1
        var r = nums[0]
        nums.forEach {
            if (it > r) r = it
        }
        var result = r
        while (l <= r) {
            val mid = (l + r) / 2
            if (check(nums, maxOperations, mid)) {
                if (mid < result) result = mid
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return result
    }

    private fun check(nums: IntArray, maxOperations: Int, max: Int): Boolean {
        var count = 0
        var i = 0
        while (i < nums.size) {
            count += nums[i] / max - if (nums[i] % max == 0) 1 else 0
            if (count > maxOperations) return false
            i++
        }
        return true
    }
}