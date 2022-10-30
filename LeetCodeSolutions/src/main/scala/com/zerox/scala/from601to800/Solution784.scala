package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/10/30 10:16
 * @note
 * 784. 字母大小写全排列 | 难度：中等
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 *
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 *
 * 示例 1：
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 示例 2:
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 *
 * 提示:
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution784 {

  /**
   * 执行用时：564 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：63 / 63
   *
   * @param s
   * @return
   */
  def letterCasePermutation(s: String): List[String] = {
    val res = new scala.collection.mutable.ListBuffer[String]
    res.addOne(s)
    val sb = new StringBuilder(s)
    backtrack(sb, res, 0)
    res.toList
  }

  def backtrack(sb: StringBuilder, res: scala.collection.mutable.ListBuffer[String], i: Int): Unit = {
    if (i >= sb.length()) return
    if (sb(i).isLower) {
      sb(i) = sb(i).toUpper
      res.addOne(sb.toString())
      backtrack(sb, res, i + 1)
      sb(i) = sb(i).toLower
    } else if (sb(i).isUpper) {
      sb(i) = sb(i).toLower
      res.addOne(sb.toString())
      backtrack(sb, res, i + 1)
      sb(i) = sb(i).toUpper
    }
    backtrack(sb, res, i + 1)
  }
}
