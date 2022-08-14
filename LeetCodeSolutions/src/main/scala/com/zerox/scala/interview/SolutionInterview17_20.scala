package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/7/12 13:38
 * @note
 * 面试题 17.20. 连续中值 | 难度：困难 | 标签：设计、双指针、数据流、排序、堆（优先队列）
 * 随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
 *
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
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/continuous-median-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_20 {
  def main(args: Array[String]): Unit = {
    val finder = new MedianFinder
    finder.addNum(1)
    finder.addNum(2)
    println(finder.findMedian())
    finder.addNum(3)
    println(finder.findMedian())
  }

  /**
   * 执行用时：2176 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：71.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：18 / 18
   *
   * 题目和 295 题、剑指 Offer 41 题一样
   */
  class MedianFinder() {

    /** initialize your data structure here. */
    private val smallMaxHeap = new scala.collection.mutable.PriorityQueue[Int]
    // PriorityQueue[Int]()(Ordering.Int.reverse) 堆顶是最小的数字
    private val bigMinHeap = new scala.collection.mutable.PriorityQueue[Int]()(Ordering.Int.reverse)

    def addNum(num: Int) {
      // PriorityQueue.head 等同于 Java 中的 peek 操作
      if (smallMaxHeap.isEmpty || num <= smallMaxHeap.head) {
        smallMaxHeap enqueue num
        if (bigMinHeap.size + 1 < smallMaxHeap.size) bigMinHeap enqueue smallMaxHeap.dequeue()
      } else {
        bigMinHeap enqueue num
        if (bigMinHeap.size > smallMaxHeap.size) smallMaxHeap enqueue bigMinHeap.dequeue()
      }
    }

    def findMedian(): Double = {
      if (smallMaxHeap.size > bigMinHeap.size) smallMaxHeap.head
      else (smallMaxHeap.head + bigMinHeap.head) / 2.0
    }

  }

  /**
   * Your MedianFinder object will be instantiated and called as such:
   * var obj = new MedianFinder()
   * obj.addNum(num)
   * var param_2 = obj.findMedian()
   */
}
