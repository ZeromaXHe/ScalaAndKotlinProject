package com.zerox.kotlin.from801to1000

/**
 * 986. 区间列表的交集 | 难度：中等 | 标签：数组、双指针
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。
 * 每个区间列表都是成对 不相交 的，并且 已经排序 。
 *
 * 返回这 两个区间列表的交集 。
 *
 * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
 *
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 *
 * 示例 1：
 * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * 示例 2：
 * 输入：firstList = [[1,3],[5,9]], secondList = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：firstList = [], secondList = [[4,8],[10,12]]
 * 输出：[]
 *
 * 示例 4：
 * 输入：firstList = [[1,7]], secondList = [[3,10]]
 * 输出：[[3,7]]
 *
 * 提示：
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 109
 * endi < starti+1
 * 0 <= startj < endj <= 109
 * endj < startj+1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 14:38
 */
object Solution986 {
    /**
     * 调试用例：
     * [[0,2],[5,10],[13,23],[24,25]]
     * [[1,5],[8,12],[15,24],[25,26]]
     *
     * [[1,3],[5,9]]
     * []
     *
     * [[3,5],[9,20]]
     * [[4,5],[7,10],[11,12],[14,15],[16,20]]
     *
     * [[0,5],[12,14],[15,18]]
     * [[11,15],[18,19]]
     *
     * 执行用时：288 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：45.7 MB, 在所有 Kotlin 提交中击败了 42.86% 的用户
     * 通过测试用例：85 / 85
     */
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        var p1 = 0
        var p2 = 0
        val result = mutableListOf<IntArray>()
        while (p1 < firstList.size && p2 < secondList.size) {
            if (firstList[p1][1] < secondList[p2][1]) {
                if (firstList[p1][1] >= secondList[p2][0]) {
                    result.add(intArrayOf(firstList[p1][0].coerceAtLeast(secondList[p2][0]), firstList[p1][1]))
                }
                p1++
            } else {
                if (secondList[p2][1] >= firstList[p1][0]) {
                    result.add(intArrayOf(firstList[p1][0].coerceAtLeast(secondList[p2][0]), secondList[p2][1]))
                }
                p2++
            }
        }
        while (p1 < firstList.size - 1 && p2 > 0) {
            p1++
            if (secondList[p2 - 1][1] >= firstList[p1][0]) {
                result.add(intArrayOf(firstList[p1][0].coerceAtLeast(secondList[p2 - 1][0]), firstList[p1][1]))
            }
        }
        while (p2 < secondList.size - 1 && p1 > 0) {
            p2++
            if (firstList[p1 - 1][1] >= secondList[p2][0]) {
                result.add(intArrayOf(firstList[p1 - 1][0].coerceAtLeast(secondList[p2][0]), secondList[p2][1]))
            }
        }
        return result.toTypedArray()
    }
}