package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/22 9:51
 * @note
 * 757. 设置交集大小至少为2 | 难度：困难 | 标签：贪心、数组、排序
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 *
 * 示例 2:
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 *
 * 注意:
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/set-intersection-size-at-least-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution757 {
  /**
   * 执行用时：628 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：116 / 116
   *
   * @param intervals
   * @return
   */
  def intersectionSizeTwo(intervals: Array[Array[Int]]): Int = {
    if (intervals.length == 1) return 2
    val sort = intervals.sortBy(_ (1))
    var lastIntersect1 = sort(0)(1) - 1
    var lastIntersect2 = sort(0)(1)
    var result = 2
    for (i <- 1 until sort.length) {
      val interval = sort(i)
      if (interval(0) > lastIntersect2) {
        result += 2
        lastIntersect1 = interval(1) - 1
        lastIntersect2 = interval(1)
      } else if (interval(0) > lastIntersect1) {
        result += 1
        lastIntersect1 = lastIntersect2
        lastIntersect2 = interval(1)
      }
    }
    result
  }

  /**
   * 以为结果必须连续，其实不需要
   *
   * @param intervals
   * @return
   */
  def intersectionSizeTwo_wrongAnswer(intervals: Array[Array[Int]]): Int = {
    if (intervals.length == 1) return 2
    intervals(0) =
      if (intervals(0)(0) > intervals(1)(0)) {
        if (intervals(0)(0) >= intervals(1)(1) - 1) Array(intervals(1)(1) - 1, intervals(0)(0) + 1)
        else Array(Math.min(intervals(0)(1), intervals(1)(1)) - 1, Math.min(intervals(0)(1), intervals(1)(1)))
      } else {
        if (intervals(0)(1) > intervals(1)(0)) Array(Math.min(intervals(0)(1), intervals(1)(1)) - 1, Math.min(intervals(0)(1), intervals(1)(1)))
        else Array(intervals(0)(1) - 1, intervals(1)(0) + 1)
      }
    val result = intervals.reduce((allIntersect, interval) => {
      if (allIntersect(0) > interval(0)) {
        if (allIntersect(0) >= interval(1)) Array(interval(1) - 1, allIntersect(1))
        else allIntersect
      } else {
        if (allIntersect(1) > interval(0)) allIntersect
        else Array(allIntersect(0), interval(0) + 1)
      }
    })
    result(1) - result(0) + 1
  }
}
