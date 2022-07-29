package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/7/29 13:59
 * @note
 * 57. 插入区间 | 难度：中等 | 标签：数组
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 *
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 *
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 提示：
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution57 {
  /**
   * 执行用时：568 ms, 在所有 Scala 提交中击败了 66.67% 的用户
   * 内存消耗：58.1 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：156 / 156
   *
   * @param intervals
   * @param newInterval
   * @return
   */
  def insert(intervals: Array[Array[Int]], newInterval: Array[Int]): Array[Array[Int]] = {
    val stack = new scala.collection.mutable.Stack[Array[Int]]
    var i = intervals.length - 1
    while (i >= 0 && intervals(i)(0) > newInterval(1)) {
      stack.push(intervals(i))
      i -= 1
    }
    intervals :+ newInterval
    if (i == -1) {
      stack.push(newInterval)
      return stack.toArray
    }
    var l = newInterval(0)
    var r = newInterval(1)
    // 合并所有和要插入的区间有交集的区间
    while (i >= 0 && intervals(i)(1) >= l) {
      l = math.min(l, intervals(i)(0))
      r = math.max(r, intervals(i)(1))
      i -= 1
    }
    stack.push(Array(l, r))
    while (i >= 0) {
      stack.push(intervals(i))
      i -= 1
    }
    stack.toArray
  }
}
