package com.zerox.offer

import com.zerox.from1to200.Solution3

/**
 * @author zhuxi
 * @since 2022/7/9 17:18
 * @note
 * 剑指 Offer 48. 最长不含重复字符的子字符串 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer48 {
  /**
   * 执行用时：528 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：987 / 987
   *
   * @param s
   * @return
   */
  def lengthOfLongestSubstring(s: String): Int = {
    Solution3.lengthOfLongestSubstring(s)
  }
}
