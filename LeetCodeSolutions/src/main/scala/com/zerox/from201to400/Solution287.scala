package com.zerox.from201to400

/**
 * @author ZeromaXHe
 * @since 2022/8/13 11:11
 * @note
 * 287. 寻找重复数 | 难度：中等 | 标签：位运算、数组、双指针、二分查找
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 提示：
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution287 {
  /**
   * 执行用时：804 ms, 在所有 Scala 提交中击败了 40.00% 的用户
   * 内存消耗：78.9 MB, 在所有 Scala 提交中击败了 20.00% 的用户
   * 通过测试用例：58 / 58
   *
   * 参考题解做的，除了二分还有快慢指针和二进制的做法
   *
   * @param nums
   * @return
   */
  def findDuplicate(nums: Array[Int]): Int = {
    val n = nums.length;
    var l = 1
    var r = n - 1
    var ans = -1;
    while (l <= r) {
      val mid = (l + r) >> 1;
      var cnt = 0;
      for (i <- 0 until n) {
        if (nums(i) <= mid) {
          cnt += 1
        }
      }
      if (cnt <= mid) {
        l = mid + 1
      } else {
        r = mid - 1
        ans = mid
      }
    }
    ans
  }
}
