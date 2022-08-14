package com.zerox.scala.from201to400

import com.zerox.scala.interview.SolutionInterview17_09

/**
 * @author zhuxi
 * @since 2022/7/9 16:52
 * @note
 * 264. 丑数 II | 难度：中等 | 标签：哈希表、数学、动态规划、堆（优先队列）
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * 1 <= n <= 1690
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution264 {
  /**
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.7 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：596 / 596
   *
   * 提交的代码没有内联 nthUglyNumber
   *
   * @param n
   * @return
   */
  def nthUglyNumber(n: Int): Int = {
    SolutionInterview17_09.nthUglyNumber(n, 2, 3, 5)
  }
}
