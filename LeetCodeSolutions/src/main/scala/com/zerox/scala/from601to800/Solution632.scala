package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 16:01
 * @note
 * 632. 最小区间 | 难度：困难 | 标签：贪心、数组、哈希表、排序、滑动窗口、堆（优先队列）
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例 1：
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 示例 2：
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 * 提示：
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] 按非递减顺序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution632 {
  /**
   * 执行用时：836 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：88 / 88
   *
   * @param nums
   * @return
   */
  def smallestRange(nums: List[List[Int]]): Array[Int] = {
    // 小顶堆
    val heap = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._1).reverse)
    var max = 0
    val numIterators = nums.map(_.iterator).toArray
    for (i <- numIterators.indices) {
      val first = numIterators(i).next()
      heap.enqueue((first, i))
      if (first > max) max = first
    }
    val result = Array(heap.head._1, max)
    while (heap.nonEmpty) {
      if (!numIterators(heap.head._2).hasNext) {
        heap.clear()
      } else {
        val (_, i) = heap.dequeue()
        val next = numIterators(i).next()
        heap.enqueue((next, i))
        if (next > max) max = next
        if (max - heap.head._1 < result(1) - result(0)) {
          result(0) = heap.head._1
          result(1) = max
        }
      }
    }
    result
  }
}
