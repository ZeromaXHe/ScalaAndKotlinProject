package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 16:45
 * @note
 * 633. 平方数之和 | 难度：中等 | 标签：数学、双指针、二分查找
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 *
 * 提示：
 * 0 <= c <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution633 {
  def main(args: Array[String]): Unit = {
    println(judgeSquareSum(3)) // false
  }

  /**
   * 执行用时：448 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：50.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：127 / 127
   *
   * @param c
   * @return
   */
  def judgeSquareSum(c: Int): Boolean = {
    var i = 0L
    var j = math.sqrt(c).toLong
    while (j >= 0) {
      val squareSum = i * i + j * j
      if (squareSum > c) j -= 1
      else if (squareSum < c) i += 1
      else return true
    }
    false
  }
}
