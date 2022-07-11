package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/6/30 19:02
 * @note
 * 面试题 17.04. 消失的数字 | 难度：简单 | 标签：位运算、数组、哈希表、数学、排序
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 *
 * 注意：本题相对书上原题稍作改动
 *
 * 示例 1：
 * 输入：[3,0,1]
 * 输出：2
 *
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/missing-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_04 {
  /**
   * 执行用时：540 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：122 / 122
   *
   * @param nums
   * @return
   */
  def missingNumber(nums: Array[Int]): Int = {
    var result = 0
    for (i <- nums.indices) {
      result += i + 1
      result -= nums(i)
    }
    result
  }
}
