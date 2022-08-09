package com.zerox.from601to800

/**
 * 611. 有效三角形的个数 | 难度：中等 | 标签：贪心、数组、双指针、二分查找、排序
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 *
 * 示例 2:
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-triangle-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 14:37
 */
object Solution611 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(triangleNumber(intArrayOf(2, 2, 3, 4))) // 3
        println(triangleNumber(intArrayOf(48, 66, 61, 46, 94, 75))) // 19
    }

    /**
     * 执行用时：280 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：45.7 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 通过测试用例：241 / 241
     */
    fun triangleNumber(nums: IntArray): Int {
        if (nums.size < 3) return 0
        nums.sort()
        var result = 0
        var i = 0
        while (i <= nums.size - 3) {
            var j = i + 1
            var k = i + 2
            while (k < nums.size) {
                if (nums[i] + nums[j] > nums[k]) {
                    if (k < nums.size - 1) {
                        // 更新 k 时需要加上所有 [j, k) 之间的元素个数
                        result += k - j
                        k++
                    } else {
                        result++
                        j++
                        if (j == k) k++
                    }
                } else if (nums[i] + nums[j] <= nums[k]) {
                    j++
                    if (j == k) k++
                }
            }
            i++
        }
        return result
    }
}