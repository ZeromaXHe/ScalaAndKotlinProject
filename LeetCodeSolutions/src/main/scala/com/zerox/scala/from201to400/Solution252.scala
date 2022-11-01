package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/11/1 18:29
 * @note
 * 252. 会议室 | 难度：简单 | 标签：数组、排序
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：false
 *
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：true
 *
 * 提示：
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution252 {
  /**
   * 执行用时：560 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：78 / 78
   *
   * @param intervals
   * @return
   */
  def canAttendMeetings(intervals: Array[Array[Int]]): Boolean = {
    intervals.sortBy(_ (0)).foldLeft(Int.MinValue)((pre, now) => if (pre > now(0)) Int.MaxValue else now(1)) != Int.MaxValue
  }
}
