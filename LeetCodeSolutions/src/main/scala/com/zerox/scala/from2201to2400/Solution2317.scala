package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/4 15:49
 * @note
 * 2317. 操作后的最大异或和 | 难度：中等 | 标签：位运算、数组、数学
 * 给你一个下标从 0 开始的整数数组 nums 。一次操作中，选择 任意 非负整数 x 和一个下标 i ，更新 nums[i] 为 nums[i] AND (nums[i] XOR x) 。
 *
 * 注意，AND 是逐位与运算，XOR 是逐位异或运算。
 *
 * 请你执行 任意次 更新操作，并返回 nums 中所有元素 最大 逐位异或和。
 *
 * 示例 1：
 * 输入：nums = [3,2,4,6]
 * 输出：7
 * 解释：选择 x = 4 和 i = 3 进行操作，num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2 。
 * 现在，nums = [3, 2, 4, 2] 且所有元素逐位异或得到 3 XOR 2 XOR 4 XOR 2 = 7 。
 * 可知 7 是能得到的最大逐位异或和。
 * 注意，其他操作可能也能得到逐位异或和 7 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,9,2]
 * 输出：11
 * 解释：执行 0 次操作。
 * 所有元素的逐位异或和为 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11 。
 * 可知 11 是能得到的最大逐位异或和。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 108
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-xor-after-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2317 {
  def main(args: Array[String]): Unit = {
    println(maximumXOR(Array(3, 2, 4, 6)) /* == 7*/)
    println(maximumXOR(Array(1, 2, 3, 9, 2)) /* == 11*/)
  }

  /**
   * 执行用时：720 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：65.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：72 / 72
   *
   * 参考题解做的…… 脑筋急转弯
   *
   * @param nums
   * @return
   */
  def maximumXOR(nums: Array[Int]): Int = {
    var ans = 0;
    for (num <- nums) ans |= num
    ans
  }
}
