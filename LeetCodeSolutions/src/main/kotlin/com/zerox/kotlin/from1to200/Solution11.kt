package com.zerox.kotlin.from1to200

/**
 * @author zhuxi
 * @since 2022/8/8 18:02
 * 11. 盛最多水的容器 | 难度：中等 | 标签：贪心、数组、双指针
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution11 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))) // 49
    }

    /**
     * 执行用时：488 ms, 在所有 Kotlin 提交中击败了 20.34% 的用户
     * 内存消耗：49.3 MB, 在所有 Kotlin 提交中击败了 41.52% 的用户
     * 通过测试用例：60 / 60
     */
    fun maxArea_slow(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var max = 0
        while (l < r) {
            if (height[l] < height[r]) {
                if (max < height[l] * (r - l)) max = height[l] * (r - l)
                l += 1
            } else {
                if (max < height[r] * (r - l)) max = height[r] * (r - l)
                r -= 1
            }
        }
        return max
    }

    /**
     * 执行用时：372 ms, 在所有 Kotlin 提交中击败了 95.76% 的用户
     * 内存消耗：45.8 MB, 在所有 Kotlin 提交中击败了 90.68% 的用户
     * 通过测试用例：60 / 60
     */
    fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var max = 0
        while (l < r) {
            if (height[l] < height[r]) {
                max = max.coerceAtLeast(height[l] * (r - l))
                l++
            } else {
                max = max.coerceAtLeast(height[r] * (r - l))
                r--
            }
        }
        return max
    }
}