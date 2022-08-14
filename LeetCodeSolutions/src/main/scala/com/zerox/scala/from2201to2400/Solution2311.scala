package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/8/1 11:01
 * @note
 * 2311. 小于等于 K 的最长二进制子序列 | 难度：中等 | 标签：贪心、记忆化搜索、字符串、动态规划
 * 给你一个二进制字符串 s 和一个正整数 k 。
 *
 * 请你返回 s 的 最长 子序列，且该子序列对应的 二进制 数字小于等于 k 。
 *
 * 注意：
 * 子序列可以有 前导 0 。
 * 空字符串视为 0 。
 * 子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
 *
 * 示例 1：
 * 输入：s = "1001010", k = 5
 * 输出：5
 * 解释：s 中小于等于 5 的最长子序列是 "00010" ，对应的十进制数字是 2 。
 * 注意 "00100" 和 "00101" 也是可行的最长子序列，十进制分别对应 4 和 5 。
 * 最长子序列的长度为 5 ，所以返回 5 。
 *
 * 示例 2：
 * 输入：s = "00101001", k = 1
 * 输出：6
 * 解释："000001" 是 s 中小于等于 1 的最长子序列，对应的十进制数字是 1 。
 * 最长子序列的长度为 6 ，所以返回 6 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i] 要么是 '0' ，要么是 '1' 。
 * 1 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2311 {
  /**
   * 执行用时：484 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：153 / 153
   *
   * @param s
   * @param k
   * @return
   */
  def longestSubsequence(s: String, k: Int): Int = {
    val n = s.length
    val m = 32 - Integer.numberOfLeadingZeros(k)
    if (n < m) n
    else s.substring(0, n - m).count(_ == '0') + (if (Integer.parseInt(s.substring(n - m), 2) <= k) m else m - 1)
  }
}
