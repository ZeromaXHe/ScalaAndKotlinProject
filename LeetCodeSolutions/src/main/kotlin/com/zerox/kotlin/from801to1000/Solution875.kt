package com.zerox.kotlin.from801to1000

/**
 * 875. 爱吃香蕉的珂珂 | 难度：中等 | 标签：数组、二分查找
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 *
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 *
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 * 提示：
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 11:43
 */
object Solution875 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(minEatingSpeed(intArrayOf(312884470), 968709470))
    }

    /**
     * 执行用时：288 ms, 在所有 Kotlin 提交中击败了 47.17% 的用户
     * 内存消耗：37.3 MB, 在所有 Kotlin 提交中击败了 94.34% 的用户
     * 通过测试用例：122 / 122
     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var sum = 0L
        var max = 0
        piles.forEach {
            sum += it
            if (it > max) max = it
        }
        var l = (sum / h).toInt().coerceAtLeast(1)
        var r = max
        var result = r
        while (l <= r) {
            val mid = (l + r) / 2
            var count = 0
            piles.forEach {
                count += it / mid
                if (it % mid > 0) count++
            }
            if (count > h) {
                l = mid + 1
            } else {
                result = mid
                r = mid - 1
            }
        }
        return result
    }
}