package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/15 14:57
 * @note
 * 剑指 Offer 03. 数组中重复的数字 | 难度：简单 | 标签：数组、哈希表、排序
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer03 {
  /**
   * 执行用时：708 ms, 在所有 Scala 提交中击败了 28.57% 的用户
   * 内存消耗：67.5 MB, 在所有 Scala 提交中击败了 19.05% 的用户
   * 通过测试用例：25 / 25
   *
   * 其实还可以用原地哈希的办法
   *
   * @param nums
   * @return
   */
  def findRepeatNumber(nums: Array[Int]): Int = {
    val set = new scala.collection.mutable.HashSet[Int]
    for (num <- nums) {
      if (set(num)) return num
      set += num
    }
    -1
  }
}
