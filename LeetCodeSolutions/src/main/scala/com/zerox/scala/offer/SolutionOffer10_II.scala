package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/15 17:37
 * @note
 * 剑指 Offer 10- II. 青蛙跳台阶问题 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 *
 * 提示：
 * 0 <= n <= 100
 * 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer10_II {
  val MOD: Int = (1e9 + 7).toInt

  /**
   * 执行用时：400 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：51 / 51
   *
   * @param n
   * @return
   */
  def numWays(n: Int): Int = {
    if (n <= 1) 1
    else numWays(n, 1, 1)
  }

  @scala.annotation.tailrec
  private def numWays(n: Int, a: Int, b: Int): Int = {
    if (n <= 1) b
    else numWays(n - 1, b, (a + b) % MOD)
  }
}
