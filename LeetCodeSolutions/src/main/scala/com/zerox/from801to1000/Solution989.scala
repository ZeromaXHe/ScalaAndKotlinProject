package com.zerox.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/8/13 10:53
 * @note
 * 989. 数组形式的整数加法 | 难度：简单 | 标签：数组、数学
 * 整数的 数组形式  num 是按照从左到右的顺序表示其数字的数组。
 *
 * 例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。
 * 给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。
 *
 * 示例 1：
 * 输入：num = [1,2,0,0], k = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 *
 * 示例 2：
 * 输入：num = [2,7,4], k = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 *
 * 示例 3：
 * 输入：num = [2,1,5], k = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 *
 * 提示：
 * 1 <= num.length <= 104
 * 0 <= num[i] <= 9
 * num 不包含任何前导零，除了零本身
 * 1 <= k <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution989 {
  /**
   * 执行用时：676 ms, 在所有 Scala 提交中击败了 25.00% 的用户
   * 内存消耗：54.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：152 / 152
   *
   * @param num
   * @param k
   * @return
   */
  def addToArrayForm(num: Array[Int], k: Int): List[Int] = {
    var length = 0
    var p = num.length - 1
    var vk = k
    val result = new scala.collection.mutable.ListBuffer[Int]
    var carry = 0
    while (p >= 0 && vk > 0) {
      val sum = carry + num(p) + vk % 10
      result prepend sum % 10
      carry = sum / 10
      vk /= 10
      length += 1
      p -= 1
    }
    while (p >= 0) {
      val sum = carry + num(p)
      result prepend sum % 10
      carry = sum / 10
      length += 1
      p -= 1
    }
    while (vk > 0) {
      val sum = carry + vk % 10
      result prepend sum % 10
      carry = sum / 10
      vk /= 10
      length += 1
    }
    if (carry > 0) {
      result prepend carry
      length += 1
    }
    result.toList
  }
}
