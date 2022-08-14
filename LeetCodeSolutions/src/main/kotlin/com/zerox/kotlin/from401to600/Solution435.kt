package com.zerox.kotlin.from401to600

/**
 * 435. 无重叠区间 | 难度：中等 | 标签：贪心、数组、动态规划、排序
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 提示:
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 14:04
 */
object Solution435 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 二维数组初始化是真的恶心
        val intervals = arrayOf(
            intArrayOf(-52, 31), intArrayOf(-73, -26), intArrayOf(82, 97), intArrayOf(-65, -11),
            intArrayOf(-62, -49), intArrayOf(95, 99), intArrayOf(58, 95), intArrayOf(-31, 49), intArrayOf(66, 98),
            intArrayOf(-63, 2), intArrayOf(30, 47), intArrayOf(-40, -26)
        )
        intervals.sortBy { it[0] }
        intervals.forEach { print("[${it[0]}, ${it[1]}], ") }
        println()
        println(eraseOverlapIntervals(intervals))
    }

    /**
     * 执行用时：760 ms, 在所有 Kotlin 提交中击败了 85.71% 的用户
     * 内存消耗：85.8 MB, 在所有 Kotlin 提交中击败了 78.57% 的用户
     * 通过测试用例：58 / 58
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortBy { it[0] }
        var result = 0
        var last = intervals[0]
        for (i in 1 until intervals.size) {
            if (last[1] > intervals[i][0]) {
                result++
                if (intervals[i][1] < last[1]) {
                    last = intervals[i]
                }
            } else {
                last = intervals[i]
            }
        }
        return result
    }
}