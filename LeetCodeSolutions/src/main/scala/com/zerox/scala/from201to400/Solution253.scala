package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/11/1 18:48
 * @note
 * 253. 会议室 II | 难度：中等 | 标签：贪心、数组、双指针、前缀和、排序、堆（优先队列）
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 *
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *
 * 提示：
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-rooms-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution253 {
  /**
   * 执行用时：556 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：59 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：78 / 78
   *
   * @param intervals
   * @return
   */
  def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
    val heap = new scala.collection.mutable.PriorityQueue[Int]()(Ordering.Int.reverse)
    var res = 0
    for (elem <- intervals.sortBy(i => (i(0), i(1)))) {
      while (heap.nonEmpty && heap.head <= elem(0)) {
        heap.dequeue()
      }
      heap.enqueue(elem(1))
      if (heap.size > res) res = heap.size
    }
    res
  }
}
