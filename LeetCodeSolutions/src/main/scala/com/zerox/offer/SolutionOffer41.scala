package com.zerox.offer

import com.zerox.interview.SolutionInterview17_20

/**
 * @author zhuxi
 * @since 2022/7/12 14:10
 * @note
 * 剑指 Offer 41. 数据流中的中位数 | 难度：困难 | 标签：设计、双指针、数据流、排序、堆（优先队列）
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
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
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer41 {
  /**
   * 执行用时：1992 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：74.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：18 / 18
   */
  class MedianFinder() {

    /** initialize your data structure here. */
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
