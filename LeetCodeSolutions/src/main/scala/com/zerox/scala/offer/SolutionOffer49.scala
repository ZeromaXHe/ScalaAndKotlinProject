package com.zerox.scala.offer

import com.zerox.scala.interview.SolutionInterview17_09

/**
 * @author zhuxi
 * @since 2022/7/9 17:00
 * @note
 * 剑指 Offer 49. 丑数 | 难度：中等 | 标签：哈希表、数学、动态规划、堆（优先队列）
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer49 {
  /**
   * 执行用时：468 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：596 / 596
   *
   * 提交的是内联 2, 3, 5 后的代码
   *
   * @param n
   * @return
   */
  def nthUglyNumber(n: Int): Int = {
    SolutionInterview17_09.nthUglyNumber(n, 2, 3, 5)
  }
}
