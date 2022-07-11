package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/6/28 18:47
 * @note
 * 4. 寻找两个正序数组的中位数 | 难度：困难 | 标签：数组、二分查找、分治
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution4 {
  def main(args: Array[String]): Unit = {
    println(findMedianSortedArrays(Array.empty, Array(1)))
    println(findMedianSortedArrays(Array(0, 0, 0, 0, 0), Array(-1, 0, 0, 0, 0, 0, 1)))
  }

  /**
   * 执行用时：668 ms, 在所有 Scala 提交中击败了 75.76% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 72.73% 的用户
   * 通过测试用例：2094 / 2094
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1)
    }
    val m = nums1.length
    val n = nums2.length
    // nums1 分界线落在 left 和 right 中点
    var left = 0
    var right = m
    // 总体偏小部分的最大值
    var median1 = 0
    // 总体偏大部分的最小值
    var median2 = 0
    while (left <= right) {
      // nums1 偏小部分个数
      val i = (left + right) / 2
      // nums2 偏小部分个数，这个是通过总体上偏小部分（nums1 的偏小加上 nums2 的偏小）要等于偏大部分的数量推导出来的
      // 即:(1) 总长度偶数时 i + j = m - i + n - j => j = (m + n) / 2 - i
      // (2) 总长度奇数时，令偏小部分的数量比偏大部分大 1，最后取了偏小部分最大值 median1 作为中位数
      // 所以 i + j = m - i + n - j + 1 => j = (m + n + 1) / 2 - i
      // 两种情况可以合并为 (m + n + 1) / 2 - i
      val j = (m + n + 1) / 2 - i;
      val nums_im1 = if (i == 0) Int.MinValue else nums1(i - 1)
      val nums_i = if (i == m) Int.MaxValue else nums1(i)
      val nums_jm1 = if (j == 0) Int.MinValue else nums2(j - 1)
      val nums_j = if (j == n) Int.MaxValue else nums2(j)

      if (nums_im1 <= nums_j) {
        median1 = Math.max(nums_im1, nums_jm1)
        median2 = Math.min(nums_i, nums_j)
        left = i + 1
      } else {
        right = i - 1
      }
    }
    if ((m + n) % 2 == 0) (median1 + median2) / 2.0
    else median1
  }

  /**
   * 执行用时：688 ms, 在所有 Scala 提交中击败了 42.42% 的用户
   * 内存消耗：55.5 MB, 在所有 Scala 提交中击败了 78.79% 的用户
   * 通过测试用例：2094 / 2094
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def findMedianSortedArrays_sort(nums1: Array[Int], nums2: Array[Int]): Double = {
    val nums3 = (nums1 ++ nums2).sorted
    (nums3((nums3.length - 1) / 2) + nums3(nums3.length / 2)) / 2.0
  }
}
