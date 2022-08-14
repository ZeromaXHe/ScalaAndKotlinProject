package com.zerox.kotlin.from1to200

/**
 * 56. 合并区间 | 难度：中等 | 标签：数组、排序
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 16:40
 */
object Solution56 {
    /**
     * 执行用时：356 ms, 在所有 Kotlin 提交中击败了 21.13% 的用户
     * 内存消耗：52.3 MB, 在所有 Kotlin 提交中击败了 8.45% 的用户
     * 通过测试用例：169 / 169
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf(intervals[0].clone())
        for (i in 1 until intervals.size) {
            if (result.last()[1] >= intervals[i][0]) {
                result.last()[1] = result.last()[1].coerceAtLeast(intervals[i][1])
            } else {
                result.add(intervals[i].clone())
            }
        }
        return result.toTypedArray()
    }
}