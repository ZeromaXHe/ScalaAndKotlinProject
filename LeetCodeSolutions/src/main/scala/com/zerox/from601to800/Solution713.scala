package com.zerox.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/8/13 12:43
 * @note
 * 713. 乘积小于 K 的子数组 | 难度：中等 | 标签：数组、滑动窗口
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 * 提示: 
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution713 {
  def main(args: Array[String]): Unit = {
    println(numSubarrayProductLessThanK(Array(10, 5, 2, 6), 100))
    println(numSubarrayProductLessThanK(Array(2, 1, 4, 9, 6, 5, 10, 5, 6, 1, 7, 10, 2, 3, 10), 423))
  }

  /**
   * 执行用时：692 ms, 在所有 Scala 提交中击败了 28.57% 的用户
   * 内存消耗：60.3 MB, 在所有 Scala 提交中击败了 64.29% 的用户
   * 通过测试用例：97 / 97
   *
   * @param nums
   * @param k
   * @return
   */
  def numSubarrayProductLessThanK(nums: Array[Int], k: Int): Int = {
    if (k <= 1) return 0
    var prod = 1
    var i = 0
    var j = 0
    var count = 0
    while (i < nums.length) {
      while (j < nums.length && prod < k) {
        prod *= nums(j)
        j += 1
      }
      count += j - i - (if (prod >= k) 1 else 0)
      prod /= nums(i)
      i += 1
    }
    count
  }
}
