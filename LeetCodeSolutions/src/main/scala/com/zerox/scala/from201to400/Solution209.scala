package com.zerox.scala.from201to400

/**
 * @author ZeromaXHe
 * @since 2022/8/13 13:13
 * @note
 * 209. 长度最小的子数组 | 难度：中等 | 标签：数组、二分查找、前缀和、滑动窗口
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution209 {
  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 46.67% 的用户
   * 内存消耗：68 MB, 在所有 Scala 提交中击败了 6.67% 的用户
   * 通过测试用例：20 / 20
   *
   * @param target
   * @param nums
   * @return
   */
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    var sum = 0
    var i = 0
    var j = 0
    var min = nums.length
    while (j < nums.length) {
      while (j < nums.length && sum < target) {
        sum += nums(j)
        j += 1
      }
      min = math.min(min, j - i)
      sum -= nums(i)
      i += 1
    }
    if (sum + nums(0) < target && i == 1 && j == nums.length) {
      return 0
    }
    while (sum >= target && i < nums.length) {
      min = math.min(min, j - i)
      sum -= nums(i)
      i += 1
    }
    min
  }
}
