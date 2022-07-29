package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/29 15:22
 * @note
 * 2272. 最大波动的子字符串 | 难度：困难 | 标签：数组、动态规划
 * 字符串的 波动 定义为子字符串中出现次数 最多 的字符次数与出现次数 最少 的字符次数之差。
 *
 * 给你一个字符串 s ，它只包含小写英文字母。请你返回 s 里所有 子字符串的 最大波动 值。
 *
 * 子字符串 是一个字符串的一段连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aababbb"
 * 输出：3
 * 解释：
 * 所有可能的波动值和它们对应的子字符串如以下所示：
 * - 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
 * - 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
 * - 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
 * - 波动值为 3 的子字符串 "babbb" 。
 * 所以，最大可能波动值为 3 。
 *
 * 示例 2：
 * 输入：s = "abcde"
 * 输出：0
 * 解释：
 * s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s  只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/substring-with-largest-variance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2272 {
  /**
   * 执行用时：632 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：138 / 138
   *
   * 参考题解做的。感觉要想到 diffWithB 这种构造还是比较难……
   * 遍历所有字符组合（26 * 26）然后求两个字符的最大波动值的最大值，这样思路估计好想一些
   * 做了些题目，感觉说白了就是对于常数复杂度的范围的话，就想办法遍历或者二分完事了。
   *
   * @param s
   * @return
   */
  def largestVariance(s: String): Int = {
    // 假设出现次数最多的字符为 a，出现次数最少的字符为 b
    // diff 维护 a 和 b 的出现次数之差
    val diff = Array.ofDim[Int](26, 26)
    // diffWithB 维护包含了 b 的 a 和 b 的出现次数之差
    val diffWithB = Array.fill(26)(Array.fill(26)(-s.length))
    var ans = 0
    for (c <- s;
         i = c - 'a';
         j <- 0 until 26 if j != i) {
      diff(i)(j) += 1
      diffWithB(i)(j) += 1
      diff(j)(i) -= 1
      diffWithB(j)(i) = diff(j)(i)
      diff(j)(i) = math.max(diff(j)(i), 0)
      ans = math.max(ans, math.max(diffWithB(i)(j), diffWithB(j)(i)))
    }
    ans
  }
}
