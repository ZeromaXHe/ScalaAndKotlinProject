package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/21 11:30
 * @note
 * 215. 数组中的第K个最大元素 | 难度：中等 | 标签：数组、分治、快速选择、排序、堆（优先队列）
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution215 {
  /**
   * 执行用时：728 ms, 在所有 Scala 提交中击败了 73.68% 的用户
   * 内存消耗：74.5 MB, 在所有 Scala 提交中击败了 10.53% 的用户
   * 通过测试用例：39 / 39
   *
   * @param nums
   * @param k
   * @return
   */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    qSort(nums, 0, nums.length - 1, nums.length - k + 1)
  }

  def qSort(nums: Array[Int], low: Int, high: Int, k: Int): Int = {
    val pivot = partition(nums, low, high)
    if (pivot == k - 1) return nums(pivot)
    if (pivot >= k) qSort(nums, low, pivot - 1, k) else qSort(nums, pivot + 1, high, k)
  }

  def partition(nums: Array[Int], low: Int, high: Int): Int = {
    val mid = low + (high - low) / 2
    if (nums(low) > nums(high)) swap(nums, low, high)
    if (nums(mid) > nums(high)) swap(nums, mid, high)
    if (nums(mid) > nums(low)) swap(nums, low, mid)
    val key = nums(low)
    var l = low
    var h = high
    while (l < h) {
      while (l < h && nums(h) >= key) h -= 1
      swap(nums, l, h)
      while (l < h && nums(l) <= key) l += 1
      swap(nums, l, h)
    }
    l
  }

  def swap(nums: Array[Int], i: Int, j: Int): Unit = {
    val temp = nums(i)
    nums(i) = nums(j)
    nums(j) = temp
  }
}
