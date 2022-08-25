package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/8/25 9:48
 * @note
 * 658. 找到 K 个最接近的元素 | 难度：中等 | 标签：数组、双指针、二分查找、排序、堆（优先队列）
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-closest-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution658 {
  /**
   * 执行用时：992 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：66 / 66
   *
   * @param arr
   * @param k
   * @param x
   * @return
   */
  def findClosestElements(arr: Array[Int], k: Int, x: Int): List[Int] = {
    val heap = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(
      Ordering.by[(Int, Int), (Int, Int)](t => (math.abs(t._1 - x), t._2)))
    for (i <- arr.indices) {
      heap.enqueue((arr(i), i))
      if (heap.size > k) heap.dequeue()
    }
    heap.map(t => arr(t._2)).toList.sorted
  }
}
