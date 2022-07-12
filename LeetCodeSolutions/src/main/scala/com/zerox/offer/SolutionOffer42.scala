package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/12 14:19
 * @note
 * 剑指 Offer 42. 连续子数组的最大和 | 难度：简单 | 标签：数组、分治、动态规划
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer42 {
  /**
   * 执行用时：616 ms, 在所有 Scala 提交中击败了 20.00% 的用户
   * 内存消耗：60.9 MB, 在所有 Scala 提交中击败了 40.00% 的用户
   * 通过测试用例：202 / 202
   *
   * @param nums
   * @return
   */
  def maxSubArray(nums: Array[Int]): Int = {
    var sum = 0
    var max = nums(0)
    for (num <- nums) {
      sum += num
      if (sum > max) max = sum
      if (sum < 0) sum = 0
    }
    max
  }
}
