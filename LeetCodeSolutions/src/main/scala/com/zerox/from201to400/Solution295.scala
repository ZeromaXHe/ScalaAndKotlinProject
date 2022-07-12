package com.zerox.from201to400

import com.zerox.interview.SolutionInterview17_20

/**
 * @author zhuxi
 * @since 2022/7/12 14:06
 * @note
 * 295. 数据流的中位数 | 难度：困难 | 标签：设计、双指针、数据流、排序、堆（优先队列）
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution295 {
  /**
   * 执行用时：1792 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：87 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：21 / 21
   */
  class MedianFinder() {
    private val finder = new SolutionInterview17_20.MedianFinder

    def addNum(num: Int) {
      finder.addNum(num)
    }

    def findMedian(): Double = {
      finder.findMedian()
    }

  }

  /**
   * Your MedianFinder object will be instantiated and called as such:
   * var obj = new MedianFinder()
   * obj.addNum(num)
   * var param_2 = obj.findMedian()
   */
}
