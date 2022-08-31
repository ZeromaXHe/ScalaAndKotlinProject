package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/8/31 11:35
 * @note
 * 1353. 最多可以参加的会议数目 | 难度：中等 | 标签：贪心、数组、堆（优先队列）
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 *
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 *
 * 请你返回你可以参加的 最大 会议数目。
 *
 * 示例 1：
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 *
 * 示例 2：
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 *
 * 提示：​​​​​​
 *
 * 1 <= events.length <= 105
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1353 {
  /**
   * 执行用时：1328 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：102.3 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：44 / 44
   *
   * 不参考题解就没想到要用堆……
   *
   * @param events
   * @return
   */
  def maxEvents(events: Array[Array[Int]]): Int = {
    val sorted = events.sorted(Ordering.by[Array[Int], Int](a => a(0)))
    // 小顶堆
    val heap = new scala.collection.mutable.PriorityQueue[Array[Int]]()(Ordering.by[Array[Int], Int](_ (1)).reverse)
    var t = sorted(0)(0)
    var i = 0
    var count = 0
    while (i < sorted.length || heap.nonEmpty) {
      while (i < sorted.length && sorted(i)(0) == t) {
        heap.enqueue(sorted(i))
        i += 1
      }
      if (heap.isEmpty) t = sorted(i)(0)
      else {
        while (heap.nonEmpty && heap.head(1) < t) heap.dequeue()
        if (heap.nonEmpty) {
          heap.dequeue()
          count += 1
        }
        t += 1
      }
    }
    count
  }
}
