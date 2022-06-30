package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 16:51
 * @note
 * 面试题 16.17. 连续数列 | 难度：简单 | 标签：数组、分治、动态规划
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶：
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/contiguous-sequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_17 {
  /**
   * 执行用时：516 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：202 / 202
   *
   * @param nums
   * @return
   */
  def maxSubArray(nums: Array[Int]): Int = {
    var sum = 0
    var max = Int.MinValue
    for (elem <- nums) {
      max = math.max(max, sum + elem)
      if (sum + elem > 0) sum += elem
      else sum = 0
    }
    max
  }
}
