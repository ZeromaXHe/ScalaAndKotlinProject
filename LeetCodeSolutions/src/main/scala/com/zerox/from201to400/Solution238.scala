package com.zerox.from201to400

/**
 * @author ZeromaXHe
 * @since 2022/8/13 11:52
 * @note
 * 238. 除自身以外数组的乘积 | 难度：中等 | 标签：数组、前缀和
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 提示：
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 *
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution238 {
  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.4 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：22 / 22
   *
   * 参考题解做的
   *
   * @param nums
   * @return
   */
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val length = nums.length;
    val ans = new Array[Int](length)

    // answer[i] 表示索引 i 左侧所有元素的乘积
    // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
    ans(0) = 1
    for (i <- 1 until length) {
      ans(i) = nums(i - 1) * ans(i - 1)
    }

    // R 为右侧所有元素的乘积
    // 刚开始右边没有元素，所以 R = 1
    var R = 1;
    for (i <- length - 1 to 0 by -1) {
      // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
      ans(i) = ans(i) * R;
      // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
      R *= nums(i)
    }
    ans
  }
}
