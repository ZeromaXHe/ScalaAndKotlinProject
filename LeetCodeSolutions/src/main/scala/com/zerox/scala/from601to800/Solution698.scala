package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/9/20 9:57
 * @note
 * 698. 划分为k个相等的子集 | 难度：中等 | 标签：位运算、记忆化搜索、数组、动态规划、回溯、状态压缩
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution698 {
  /**
   * 执行用时：1080 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：162 / 162
   *
   * 参考 473. 火柴拼正方形
   *
   * @param nums
   * @param k
   * @return
   */
  def canPartitionKSubsets(nums: Array[Int], k: Int): Boolean = {
    val totalLen = nums.sum
    if (totalLen % k != 0) return false
    val len = totalLen / k
    val n = nums.length
    val dp = Array.fill(1 << n)(-1)
    dp(0) = 0
    for (s <- 1 until (1 << n)) {
      var break = false
      for (k <- 0 until n if (s & (1 << k)) != 0 && !break) {
        val s1 = s & ~(1 << k)
        if (dp(s1) >= 0 && dp(s1) + nums(k) <= len) {
          dp(s) = (dp(s1) + nums(k)) % len
          break = true
        }
      }
    }
    dp((1 << n) - 1) == 0
  }
}

