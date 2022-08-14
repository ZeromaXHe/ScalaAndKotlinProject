package com.zerox.scala.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/8/13 11:59
 * @note
 * 560. 和为 K 的子数组 | 难度：中等 | 标签：数组、哈希表、前缀和
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution560 {
  /**
   * 执行用时：696 ms, 在所有 Scala 提交中击败了 66.67% 的用户
   * 内存消耗：60.4 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：92 / 92
   *
   * 参考题解做的
   *
   * @param nums
   * @param k
   * @return
   */
  def subarraySum(nums: Array[Int], k: Int): Int = {
    var count = 0
    var pre = 0;
    val mp = new scala.collection.mutable.HashMap[Int, Int]
    mp(0) = 1
    for (i <- nums.indices) {
      pre += nums(i)
      if (mp.contains(pre - k)) {
        count += mp(pre - k)
      }
      mp(pre) = mp.getOrElse(pre, 0) + 1
    }
    count
  }
}
