package com.zerox.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 10:35
 * @note
 * 1410. HTML 实体解析器 | 难度：中等 | 标签：哈希表、字符串
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 *
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 *
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 *
 * 示例 1：
 * 输入：text = "&amp; is an HTML entity but &ambassador; is not."
 * 输出："& is an HTML entity but &ambassador; is not."
 * 解释：解析器把字符实体 &amp; 用 & 替换
 *
 * 示例 2：
 * 输入：text = "and I quote: &quot;...&quot;"
 * 输出："and I quote: \"...\""
 *
 * 示例 3：
 * 输入：text = "Stay home! Practice on Leetcode :)"
 * 输出："Stay home! Practice on Leetcode :)"
 *
 * 示例 4：
 * 输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
 * 输出："x > y && x < y is always false"
 *
 * 示例 5：
 * 输入：text = "leetcode.com&frasl;problemset&frasl;all"
 * 输出："leetcode.com/problemset/all"
 *
 * 提示：
 * 1 <= text.length <= 10^5
 * 字符串可能包含 256 个ASCII 字符中的任意字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/html-entity-parser
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1410 {
  /**
   * 执行用时：736 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：158 / 158
   *
   * @param text
   * @return
   */
  def entityParser(text: String): String = {
    val sb = StringBuilder.newBuilder
    var i = 0
    while (i < text.length) {
      if (text(i) == '&' && i < text.length - 3) {
        text(i + 1) match {
          case 'q' =>
            if (i + 5 < text.length && text(i + 2) == 'u' && text(i + 3) == 'o'
              && text(i + 4) == 't' && text(i + 5) == ';') {
              sb.append('\"')
              i += 5
            } else sb.append(text(i))
          case 'a' =>
            if (i + 5 < text.length && text(i + 2) == 'p' && text(i + 3) == 'o'
              && text(i + 4) == 's' && text(i + 5) == ';') {
              sb.append('\'')
              i += 5
            } else if (i + 4 < text.length && text(i + 2) == 'm'
              && text(i + 3) == 'p' && text(i + 4) == ';') {
              sb.append('&')
              i += 4
            } else sb.append(text(i))
          case 'g' =>
            if (i + 3 < text.length && text(i + 2) == 't' && text(i + 3) == ';') {
              sb.append('>')
              i += 3
            } else sb.append(text(i))
          case 'l' =>
            if (i + 3 < text.length && text(i + 2) == 't' && text(i + 3) == ';') {
              sb.append('<')
              i += 3
            } else sb.append(text(i))
          case 'f' =>
            if (i + 6 < text.length && text(i + 2) == 'r' && text(i + 3) == 'a'
              && text(i + 4) == 's' && text(i + 5) == 'l' && text(i + 6) == ';') {
              sb.append('/')
              i += 6
            } else sb.append(text(i))
          case _ =>
            sb.append(text(i))
        }
      } else {
        sb.append(text(i))
      }
      i += 1
    }
    sb.toString()
  }
}
