package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 11:45
 * @note
 * 628. 三个数的最大乘积 | 难度：简单 | 标签：数组、数学、排序
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 * 提示：
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution628 {
  def main(args: Array[String]): Unit = {
    println(maximumProduct(Array(-1, -2, -3, -4))) // -6
  }

  /**
   * 执行用时：616 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：92 / 92
   *
   * @param nums
   * @return
   */
  def maximumProduct(nums: Array[Int]): Int = {
    val sorted = nums.sorted
    val n = sorted.length
    if (sorted(n - 1) > 0) sorted(n - 1) * math.max(sorted(n - 2) * sorted(n - 3), sorted(0) * sorted(1))
    else sorted(n - 1) * sorted(n - 2) * sorted(n - 3)
  }
}
