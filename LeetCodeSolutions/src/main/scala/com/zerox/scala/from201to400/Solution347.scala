package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/8/26 12:00
 * @note
 * 347. 前 K 个高频元素 | 难度：中等 | 标签：数组、哈希表、分治、桶排序、计数、快速选择、排序、堆（优先队列）
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution347 {
  /**
   * 执行用时：636 ms, 在所有 Scala 提交中击败了 66.67% 的用户
   * 内存消耗：60.4 MB, 在所有 Scala 提交中击败了 22.22% 的用户
   * 通过测试用例：21 / 21
   *
   * @param nums
   * @param k
   * @return
   */
  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val map = new scala.collection.mutable.HashMap[Int, Int]
    for (num <- nums) {
      map.put(num, map.getOrElse(num, 0) + 1)
    }
    // 小顶堆
    val queue = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._2).reverse)
    for ((num, count) <- map) {
      if (queue.size == k) {
        if (queue.head._2 < count) {
          queue.dequeue()
          queue.enqueue((num, count))
        }
      } else {
        queue.enqueue((num, count))
      }
    }
    (for (_ <- 0 until k) yield {
      queue.dequeue()._1
    }).toArray
  }
}
