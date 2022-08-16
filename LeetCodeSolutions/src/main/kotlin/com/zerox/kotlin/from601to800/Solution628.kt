package com.zerox.kotlin.from601to800

/**
 * 628. 三个数的最大乘积 | 难度：简单 | 标签：
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 * 提示：
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 17:06
 */
object Solution628 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(maximumProduct(intArrayOf(1, 2, 3)))
    }

    /**
     * 执行用时：360 ms, 在所有 Kotlin 提交中击败了 10.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Kotlin 提交中击败了 30.00% 的用户
     * 通过测试用例：92 / 92
     */
    fun maximumProduct_heap(nums: IntArray): Int {
        // 大顶堆，存储的是 nums 中最小的两个元素
        val maxHeap = java.util.PriorityQueue(3, compareBy<Int> { it }.reversed())
        // 小顶堆，存储的是 nums 中最大的三个元素
        val minHeap = java.util.PriorityQueue<Int>(4)
        nums.forEach {
            maxHeap.offer(it)
            if (maxHeap.size == 3) maxHeap.poll()
            minHeap.offer(it)
            if (minHeap.size == 4) minHeap.poll()
        }
        // 力扣上 Kotlin 1.3 只能用 max()
//        val max = minHeap.max()
        val max = minHeap.maxOrNull()
        return minHeap.reduce { acc, i -> acc * i }.coerceAtLeast(
            maxHeap.reduce { acc, i -> acc * i } * max!!)
    }

    /**
     * 执行用时：256 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Kotlin 提交中击败了 80.00% 的用户
     * 通过测试用例：92 / 92
     */
    fun maximumProduct(nums: IntArray): Int {
        // 最小的和第二小的
        var min1 = Int.MAX_VALUE
        var min2 = Int.MAX_VALUE
        // 最大的、第二大的和第三大的
        var max1 = Int.MIN_VALUE
        var max2 = Int.MIN_VALUE
        var max3 = Int.MIN_VALUE

        for (x in nums) {
            if (x < min1) {
                min2 = min1
                min1 = x
            } else if (x < min2) {
                min2 = x
            }

            if (x > max1) {
                max3 = max2
                max2 = max1
                max1 = x
            } else if (x > max2) {
                max3 = max2
                max2 = x
            } else if (x > max3) {
                max3 = x
            }
        }

        return (min1 * min2 * max1).coerceAtLeast(max1 * max2 * max3)
    }
}