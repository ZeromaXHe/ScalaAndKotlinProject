package com.zerox.offer

import com.zerox.interview.SolutionInterview17_14

/**
 * @author zhuxi
 * @since 2022/7/12 16:16
 * @note
 * 面试题40. 最小的k个数 | 难度：简单 | 标签：
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer40 {
  /**
   * 执行用时：676 ms, 在所有 Scala 提交中击败了 16.67% 的用户
   * 内存消耗：57.2 MB, 在所有 Scala 提交中击败了 16.67% 的用户
   * 通过测试用例：39 / 39
   *
   * @param arr
   * @param k
   * @return
   */
  def getLeastNumbers(arr: Array[Int], k: Int): Array[Int] = {
    SolutionInterview17_14.smallestK(arr, k)
  }

  /**
   * 执行用时：696 ms, 在所有 Scala 提交中击败了 16.67% 的用户
   * 内存消耗：57.2 MB, 在所有 Scala 提交中击败了 16.67% 的用户
   * 通过测试用例：39 / 39
   *
   * @param arr
   * @param k
   * @return
   */
  def getLeastNumbers_heapConstructor(arr: Array[Int], k: Int): Array[Int] = {
    val heap = scala.collection.mutable.PriorityQueue[Int](arr: _*)(Ordering.Int.reverse)
    (for (_ <- 0 until k) yield heap.dequeue()).toArray
  }

  /**
   * 执行用时：596 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.3 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：39 / 39
   *
   * @param arr
   * @param k
   * @return
   */
  def getLeastNumbers_sort(arr: Array[Int], k: Int): Array[Int] = {
    arr.sorted.take(k)
  }
}
