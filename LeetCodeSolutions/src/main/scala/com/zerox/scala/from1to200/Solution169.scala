package com.zerox.scala.from1to200

import com.zerox.scala.interview.SolutionInterview17_10

/**
 * @author zhuxi
 * @since 2022/7/11 14:04
 * @note
 * 169. 多数元素 | 难度：简单 | 标签：
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution169 {
  /**
   * 执行用时：584 ms, 在所有 Scala 提交中击败了 69.23% 的用户
   * 内存消耗：60.6 MB, 在所有 Scala 提交中击败了 38.46% 的用户
   * 通过测试用例：43 / 43
   *
   * @param nums
   * @return
   */
  def majorityElement_redundantCheck(nums: Array[Int]): Int = {
    SolutionInterview17_10.majorityElement(nums)
  }

  /**
   * 因为这里限制了输入一定存在多数元素，所以可以优化掉最后的校验循环
   *
   * 执行用时：560 ms, 在所有 Scala 提交中击败了 84.62% 的用户
   * 内存消耗：60.5 MB, 在所有 Scala 提交中击败了 46.15% 的用户
   * 通过测试用例：43 / 43
   *
   * @param nums
   * @return
   */
  def majorityElement(nums: Array[Int]): Int = {
    var count = 0
    var res = -1
    for (num <- nums) {
      if (count == 0) {
        res = num
        count = 1
      } else if (res == num) {
        count += 1
      } else {
        count -= 1
      }
    }
    res
  }
}
