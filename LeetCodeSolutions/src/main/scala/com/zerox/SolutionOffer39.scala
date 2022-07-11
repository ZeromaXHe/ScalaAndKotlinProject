package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/11 14:10
 * @note
 * 剑指 Offer 39. 数组中出现次数超过一半的数字 | 难度：简单 | 标签：
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * 限制：
 * 1 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer39 {
  /**
   * 执行用时：592 ms, 在所有 Scala 提交中击败了 25.00% 的用户
   * 内存消耗：60.2 MB, 在所有 Scala 提交中击败了 25.00% 的用户
   * 通过测试用例：45 / 45
   *
   * @param nums
   * @return
   */
  def majorityElement(nums: Array[Int]): Int = {
    Solution169.majorityElement(nums)
  }
}
