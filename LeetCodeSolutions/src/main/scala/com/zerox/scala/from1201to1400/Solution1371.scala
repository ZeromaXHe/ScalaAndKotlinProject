package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/9/6 10:43
 * @note
 * 1371. 每个元音包含偶数次的最长子字符串 | 难度：中等 | 标签：位运算、哈希表、字符串、前缀和
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 * 示例 1：
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 *
 * 示例 2：
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 *
 * 示例 3：
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *
 * 提示：
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1371 {
  /**
   * 执行用时：700 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：53 / 53
   *
   * 参考题解做的。只需要用位运算来计算奇偶性这个思路还是挺妙的。
   *
   * @param s
   * @return
   */
  def findTheLongestSubstring(s: String): Int = {
    val map = Map('a' -> 0, 'e' -> 1, 'i' -> 2, 'o' -> 3, 'u' -> 4)
    val pos = Array.fill(1 << 5)(-1)
    pos(0) = 0
    var res = 0
    var status = 0
    for (i <- s.indices) {
      if (map.contains(s(i))) status ^= (1 << map(s(i)))
      if (pos(status) >= 0) res = res max (i + 1 - pos(status))
      else pos(status) = i + 1
    }
    res
  }
}
