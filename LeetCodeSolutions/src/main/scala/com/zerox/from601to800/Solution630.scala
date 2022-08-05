package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 15:45
 * @note
 * 630. 课程表 III | 难度：困难 | 标签：贪心、数组、堆（优先队列）
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 *
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 *
 * 返回你最多可以修读的课程数目。
 *
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 *
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 *
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 *
 * 提示:
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/course-schedule-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution630 {
  /**
   * 执行用时：888 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：97 / 97
   *
   * 参考题解做的
   *
   * @param courses
   * @return
   */
  def scheduleCourse(courses: Array[Array[Int]]): Int = {
    val sort = courses.sortBy(_ (1))
    // 大顶堆
    val heap = new scala.collection.mutable.PriorityQueue[Int]
    var total = 0
    for (Array(ti, di) <- sort) {
      if (total + ti <= di) {
        total += ti
        heap.enqueue(ti)
      } else if (heap.nonEmpty && heap.head > ti) {
        total += ti - heap.dequeue()
        heap.enqueue(ti)
      }
    }
    heap.size
  }
}
