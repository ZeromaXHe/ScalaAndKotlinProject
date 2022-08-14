package com.zerox.kotlin.from1to200

/**
 * 169. 多数元素 | 难度：简单 | 标签：数组、哈希表、分治、计数、排序
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 13:46
 */
object Solution169 {
    /**
     * 执行用时：216 ms, 在所有 Kotlin 提交中击败了 77.42% 的用户
     * 内存消耗：41.4 MB, 在所有 Kotlin 提交中击败了 78.49% 的用户
     * 通过测试用例：43 / 43
     */
    fun majorityElement(nums: IntArray): Int {
        var result = -1
        var count = 0
        nums.forEach {
            if (result != it) {
                count--
                if (count < 0) {
                    result = it
                    count = 1
                }
            } else {
                count++
            }
        }
        return result
    }
}