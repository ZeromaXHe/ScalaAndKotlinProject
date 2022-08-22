package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/22 17:47
 * @note
 * 1508. 子数组和排序后的区间和 | 难度：中等 | 标签：数组、双指针、二分查找、排序
 * 给你一个数组 nums ，它包含 n 个正整数。你需要计算所有非空连续子数组的和，并将它们按升序排序，得到一个新的包含 n * (n + 1) / 2 个数字的数组。
 *
 * 请你返回在新数组中下标为 left 到 right （下标从 1 开始）的所有数字和（包括左右端点）。由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4], n = 4, left = 1, right = 5
 * 输出：13
 * 解释：所有的子数组和为 1, 3, 6, 10, 2, 5, 9, 3, 7, 4 。将它们升序排序后，我们得到新的数组 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 1 到 ri = 5 的和为 1 + 2 + 3 + 3 + 4 = 13 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4], n = 4, left = 3, right = 4
 * 输出：6
 * 解释：给定数组与示例 1 一样，所以新数组为 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 3 到 ri = 4 的和为 3 + 3 = 6 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4], n = 4, left = 1, right = 10
 * 输出：50
 *
 * 提示：
 * 1 <= nums.length <= 10^3
 * nums.length == n
 * 1 <= nums[i] <= 100
 * 1 <= left <= right <= n * (n + 1) / 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1508 {
  def main(args: Array[String]): Unit = {
    println(rangeSum(Array(1, 2, 3, 4), 4, 1, 5)) // 13
    println(rangeSum(Array(9, 1, 1, 7, 1), 5, 5, 5)) // 7
  }

  val MOD = (1e9 + 7).toInt

  /**
   * 执行用时：1020 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：54 / 54
   *
   * @param nums
   * @param n
   * @param left
   * @param right
   * @return
   */
  def rangeSum(nums: Array[Int], n: Int, left: Int, right: Int): Int = {
    var result = 0
    // 小顶堆
    val heap = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.Tuple2[Int, Int].reverse)
    nums.zipWithIndex.foreach(heap.enqueue(_))
    for (i <- 1 to right) {
      val deq = heap.dequeue()
      if (deq._2 + 1 < nums.length) {
        heap.enqueue((deq._1 + nums(deq._2 + 1), deq._2 + 1))
      }
      if (i >= left) result = (deq._1 + result) % MOD
    }
    result
  }
}
