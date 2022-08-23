package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/23 10:23
 * @note
 * 1498. 满足条件的子序列数目 | 难度：中等 | 标签：数组、双指针、二分查找、排序
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 *
 * 由于答案可能很大，请将结果对 109 + 7 取余后返回。
 *
 * 示例 1：
 * 输入：nums = [3,5,6,7], target = 9
 * 输出：4
 * 解释：有 4 个子序列满足该条件。
 * [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 *
 * 示例 2：
 * 输入：nums = [3,3,6,8], target = 10
 * 输出：6
 * 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 *
 * 示例 3：
 * 输入：nums = [2,3,3,4,6,7], target = 12
 * 输出：61
 * 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 * 有效序列总数为（63 - 2 = 61）
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= target <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1498 {
  def main(args: Array[String]): Unit = {
    println(numSubseq(Array(3, 3, 6, 8), 10)) // 6
    println(numSubseq(Array(7, 10, 7, 3, 7, 5, 4), 12)) // 56
  }

  val MOD: Int = (1e9 + 7).toInt

  /**
   * 执行用时：836 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：65.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：62 / 62
   *
   * @param nums
   * @param target
   * @return
   */
  def numSubseq(nums: Array[Int], target: Int): Int = {
    val sorted = nums.sorted
    val c = new Array[Int](sorted.length)
    c(0) = 1
    for (i <- 1 until c.length) c(i) = (c(i - 1) << 1) % MOD
    var l = 0
    var r = sorted.length - 1
    var result = 0
    while (l < sorted.length) {
      if (target < 2 * sorted(l)) return result
      r = binarySearch(sorted, l, r, target - sorted(l))
      result = (result + c(r - l)) % MOD
      l += 1
    }
    result
  }

  private def binarySearch(arr: Array[Int], from: Int, to: Int, value: Int): Int = {
    var l = from
    var r = to
    var result = -1
    while (l <= r) {
      val mid = l + (r - l) / 2
      if (arr(mid) > value) r = mid - 1
      else {
        l = mid + 1
        result = mid
      }
    }
    result
  }
}
