package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/9/2 14:20
 * @note
 * 828. 统计子串中的唯一字符 | 难度：困难 | 标签：哈希表、字符串、动态规划
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 *
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 * 示例 1：
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 * 其中，每一个子串都由独特字符构成。
 * 所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 *
 * 示例 2：
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 *
 * 示例 3：
 * 输入：s = "LEETCODE"
 * 输出：92
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution828 {
  /**
   * 执行用时：648 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：76 / 76
   *
   * @param s
   * @return
   */
  def uniqueLetterString(s: String): Int = {
    val last = Array.fill(26)(-1)
    val left = new Array[Int](s.length)
    for (i <- s.indices) {
      left(i) = i - last(s(i) - 'A') - 1
      last(s(i) - 'A') = i
    }
    for (i <- last.indices) last(i) = s.length
    (for (i <- s.indices.reverse) yield {
      val right = last(s(i) - 'A') - i - 1
      last(s(i) - 'A') = i
      1 + left(i) * right + left(i) + right
    }).sum
  }
}
