package com.zerox.from201to400

/**
 * @author zhuxi
 * @since 2022/7/9 15:46
 * @note
 * 300. 最长递增子序列 | 难度：中等 | 标签：数组、二分查找、动态规划
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * 进阶：
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution300 {
  /**
   * 执行用时：520 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 70.00% 的用户
   * 通过测试用例：54 / 54
   *
   * 如果只用动规很容易，加个二分的这种思路自己是真的想不到。参考题解做的。
   * 面试题 17.08 优化到最快需要用到这里递增子序列的动规+二分思路
   *
   * @param nums
   * @return
   */
  def lengthOfLIS(nums: Array[Int]): Int = {
    // 思想就是让 incr 中存储比较小的元素。这样，incr 未必是真实的最长上升子序列，但长度是对的。
    val incr = new Array[Int](nums.length)
    var res = 0
    for (num <- nums) {
      // 如果 incr 中元素都比 num 小，num 将被插到最后
      // 否则，用 num 覆盖掉比它大的元素中最小的那个。
      var i = 0
      var j = res
      while (i < j) {
        val m = (i + j) >> 1
        if (incr(m) < num) i = m + 1
        else j = m
      }
      incr(i) = num
      if (res == j) res += 1
    }
    res
  }
}
