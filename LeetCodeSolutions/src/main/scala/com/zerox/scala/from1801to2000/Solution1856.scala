package com.zerox.scala.from1801to2000

import com.zerox.scala.aider.{MonotonicStack, PreSumArray}

/**
 * @author zhuxi
 * @since 2022/9/6 15:38
 * @note
 * 1856. 子数组最小乘积的最大值 | 难度：中等 | 标签：栈、数组、前缀和、单调栈
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 *
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 *
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 *
 * 子数组 定义为一个数组的 连续 部分。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 *
 * 示例 2：
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 *
 * 示例 3：
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-subarray-min-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1856 {
  def main(args: Array[String]): Unit = {
    println(maxSumMinProduct(Array(1, 2, 3, 2))) // 14
  }

  /**
   * 执行用时：1176 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：73 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：42 / 42
   *
   * @param nums
   * @return
   */
  def maxSumMinProduct(nums: Array[Int]): Int = {
    val left = new Array[Int](nums.length)
    val right = Array.fill(nums.length)(nums.length - 1)
    val stack = new MonotonicStack[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._1))
    for (i <- nums.indices) {
      val secondTop = stack.push((nums(i), i), top => right(top._2) = i - 1)
      if (secondTop.nonEmpty) left(i) = secondTop.get._2 + 1
    }
    val preSum = new PreSumArray(nums)
    var res = 0L
    for (i <- nums.indices; sum = preSum(left(i), right(i) + 1) * nums(i) if sum > res) {
      res = sum
    }
    (res % (1e9 + 7).toInt).toInt
  }
}
