package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/9 11:31
 * @note
 * 面试题 17.06. 2出现的次数 | 难度：困难 | 标签：递归、数学、动态规划
 * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 *
 * 示例:
 *
 * 输入: 25
 * 输出: 9
 * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
 * 提示：
 *
 * n <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-2s-in-range-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_06 {
  /**
   * 执行用时：416 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：44 / 44
   *
   * 参考第 233 题、剑指 Offer 43 题
   *
   * @param n
   * @return
   */
  def numberOf2sInRange(n: Int): Int = {
    var test = 1L
    var result = 0L
    while (test <= n) {
      result += (n / (test * 10)) * test + math.min(math.max((n - 1) % (test * 10) - 2 * test + 1, 0), test)
      test *= 10
    }
    result.toInt
  }
}
