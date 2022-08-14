package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 11:28
 * @note
 * 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串 | 难度：中等 | 标签：字符串、回溯
 * 一个 「开心字符串」定义为：
 *
 * 仅包含小写字母 ['a', 'b', 'c'].
 * 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
 * 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。
 *
 * 给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
 *
 * 请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
 *
 * 示例 1：
 * 输入：n = 1, k = 3
 * 输出："c"
 * 解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。
 *
 * 示例 2：
 * 输入：n = 1, k = 4
 * 输出：""
 * 解释：长度为 1 的开心字符串只有 3 个。
 *
 * 示例 3：
 * 输入：n = 3, k = 9
 * 输出："cab"
 * 解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"] 。第 9 个字符串为 "cab"
 *
 * 示例 4：
 * 输入：n = 2, k = 7
 * 输出：""
 *
 * 示例 5：
 * 输入：n = 10, k = 100
 * 输出："abacbabacb"
 *
 * 提示：
 * 1 <= n <= 10
 * 1 <= k <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1415 {
  val choices: Array[String] = Array("bc", "ac", "ab")

  /**
   * 执行用时：424 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：345 / 345
   *
   * @param n
   * @param k
   * @return
   */
  def getHappyString(n: Int, k: Int): String = {
    var mask = 1 << (n - 1)
    if (k > 3 * mask) return ""
    val builder = StringBuilder.newBuilder
    var pre = (k - 1) / mask
    builder.append(pre match {
      case 0 => 'a'
      case 1 => 'b'
      case 2 => 'c'
    })
    val remain = k - 1 - pre * mask
    mask >>= 1
    while (builder.length < n) {
      builder.append(choices(pre)(if ((remain & mask) == 0) 0 else 1))
      pre = builder(builder.length - 1) match {
        case 'a' => 0
        case 'b' => 1
        case 'c' => 2
      }
      mask >>= 1
    }
    builder.toString()
  }
}
