package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/6/30 18:58
 * @note
 * 面试题 17.01. 不用加号的加法 | 难度：简单 | 标签：位运算、数学
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-without-plus-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_01 {
  /**
   * 执行用时：412 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：51 / 51
   *
   * @param a
   * @param b
   * @return
   */
  @scala.annotation.tailrec
  def add(a: Int, b: Int): Int = {
    if (a == 0) b
    else add((a & b) << 1, a ^ b)
  }
}
