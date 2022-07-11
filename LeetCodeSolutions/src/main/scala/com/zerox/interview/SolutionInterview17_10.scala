package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/11 13:48
 * @note
 * 面试题 17.10. 主要元素 | 难度：简单 | 标签：数组、计数
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 * 示例 1：
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 *
 * 示例 2：
 * 输入：[3,2]
 * 输出：-1
 *
 * 示例 3：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-majority-element-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_10 {
  def main(args: Array[String]): Unit = {
    println(majorityElement(Array(1, 2, 3)) == -1)
  }

  /**
   * 执行用时：584 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：46 / 46
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
    if (count > 0 && nums.count(_ == res) > nums.length / 2) res else -1
  }
}
