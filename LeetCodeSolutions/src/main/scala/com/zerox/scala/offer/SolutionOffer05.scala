package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/15 15:18
 * @note
 * 剑指 Offer 05. 替换空格 | 难度：简单 | 标签：字符串
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer05 {
  /**
   * 执行用时：460 ms, 在所有 Scala 提交中击败了 70.00% 的用户
   * 内存消耗：51.9 MB, 在所有 Scala 提交中击败了 80.00% 的用户
   * 通过测试用例：27 / 27
   *
   * 好像用流 s.map(ch => if (ch == ' ') "%20" else ch).mkString 以及 s.replace(" ", "%20") 会快一些
   *
   * @param s
   * @return
   */
  def replaceSpace(s: String): String = {
    val builder = new StringBuilder
    for (ch <- s) {
      builder.append(if (ch == ' ') "%20" else ch)
    }
    builder.toString()
  }
}
