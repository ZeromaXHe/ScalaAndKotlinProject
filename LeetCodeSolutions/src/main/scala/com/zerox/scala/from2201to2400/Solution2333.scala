package com.zerox.scala.from2201to2400

import com.zerox.scala.contest.BiweeklyContest82

/**
 * @author zhuxi
 * @since 2022/8/29 18:40
 * @note
 * 2333. 最小差值平方和 | 难度：中等 | 标签：数组、数学、排序、堆（优先队列）
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
 *
 * 数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])2 之和。
 *
 * 同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
 *
 * 请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
 *
 * 注意：你可以将数组中的元素变成 负 整数。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
 * 输出：579
 * 解释：nums1 和 nums2 中的元素不能修改，因为 k1 = 0 和 k2 = 0 。
 * 差值平方和为：(1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579 。
 *
 * 示例 2：
 * 输入：nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
 * 输出：43
 * 解释：一种得到最小差值平方和的方式为：
 * - 将 nums1[0] 增加一次。
 * - 将 nums2[2] 增加一次。
 * 最小差值平方和为：
 * (2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43 。
 * 注意，也有其他方式可以得到最小差值平方和，但没有得到比 43 更小答案的方案。
 *
 * 提示：
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 0 <= nums1[i], nums2[i] <= 105
 * 0 <= k1, k2 <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-sum-of-squared-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2333 {
  def minSumSquareDiff(nums1: Array[Int], nums2: Array[Int], k1: Int, k2: Int): Long = {
    BiweeklyContest82.minSumSquareDiff(nums1, nums2, k1, k2)
  }

  def minSumSquareDiff_timeout(nums1: Array[Int], nums2: Array[Int], k1: Int, k2: Int): Long = {
    if (nums1.length == 1) {
      val abs = math.max(math.abs(nums1(0).toLong - nums2(0)) - k1 - k2, 0)
      return abs * abs
    }
    // 大顶堆
    val heap = new scala.collection.mutable.PriorityQueue[Int]
    for (i <- nums1.indices) {
      heap.enqueue(math.abs(nums1(i) - nums2(i)))
    }
    var k = k1 + k2
    while (heap.head > 0 && k > 0) {
      val deq = heap.dequeue()
      val diff = deq - heap.head + 1
      heap.enqueue(math.max(0, deq - diff))
      k -= diff
    }
    var sum = 0L
    heap.foreach(i => sum += i.toLong * i)
    sum
  }
}
