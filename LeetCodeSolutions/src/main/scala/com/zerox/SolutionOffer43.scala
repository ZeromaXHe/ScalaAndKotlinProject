package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/9 11:18
 * @note
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数 | 难度：困难 | 标签：递归、数学、动态规划
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 *
 * 限制：
 * 1 <= n < 2^31
 *
 * 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer43 {
  /**
   * 执行用时：408 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：39 / 39
   *
   * @param n
   * @return
   */
  def countDigitOne(n: Int): Int = {
    var test = 1L
    var result = 0L
    while (n >= test) {
      result += (n / (test * 10)) * test + math.min(math.max(n % (test * 10) - test + 1, 0), test)
      test *= 10
    }
    result.toInt
  }
}
