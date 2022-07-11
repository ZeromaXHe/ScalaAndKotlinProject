package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/11 15:29
 * @note
 * 面试题 17.14. 最小K个数 | 难度：中等 | 标签：数组、分治、快速选择、排序、堆（优先队列）
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 *
 * 提示：
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_14 {
  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：66.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：29 / 29
   *
   * @param arr
   * @param k
   * @return
   */
  def smallestK(arr: Array[Int], k: Int): Array[Int] = {
    val heap = new scala.collection.mutable.PriorityQueue[Int]()(Ordering.Int.reverse) ++= arr
    (for (_ <- 0 until k) yield heap.dequeue()).toArray
  }
}
