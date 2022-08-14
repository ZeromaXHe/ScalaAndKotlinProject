package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/8/1 9:43
 * @note
 * 1374. 生成每种字符都是奇数个的字符串 | 难度：简单 | 标签：字符串
 * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 *
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出："pppz"
 * 解释："pppz" 是一个满足题目要求的字符串，因为 'p' 出现 3 次，且 'z' 出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ohhh" 和 "love"。
 *
 * 示例 2：
 * 输入：n = 2
 * 输出："xy"
 * 解释："xy" 是一个满足题目要求的字符串，因为 'x' 和 'y' 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ag" 和 "ur"。
 *
 * 示例 3：
 * 输入：n = 7
 * 输出："holasss"
 *
 * 提示：
 * 1 <= n <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1374 {
  /**
   * 执行用时：424 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：103 / 103
   *
   * @param n
   * @return
   */
  def generateTheString(n: Int): String = {
    if (n % 2 == 1) "a" * n
    else "a" * (n - 1) + "b"
  }
}
