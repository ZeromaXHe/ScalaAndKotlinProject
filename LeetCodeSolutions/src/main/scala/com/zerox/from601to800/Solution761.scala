package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/8/8 9:41
 * @note
 * 761. 特殊的二进制序列 | 难度：困难 | 标签：递归、字符串
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 *
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * 示例 1:
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 *
 * 说明:
 * S 的长度不超过 50。
 * S 保证为一个满足上述定义的特殊 的二进制序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/special-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution761 {
  def main(args: Array[String]): Unit = {
    println(makeLargestSpecial("11011000")) // "11100100"
  }

  /**
   * 执行用时：516 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：86 / 86
   *
   * @param s
   * @return
   */
  def makeLargestSpecial(s: String): String = {
    makeLargestSpecial(s, 0, s.length)
  }

  def makeLargestSpecial(s: String, from: Int, to: Int): String = {
    if (to == from) return ""
    var count = 0
    var last = from
    var cur = from
    val list = new scala.collection.mutable.ListBuffer[String]
    for (i <- from until to) {
      if (s(i) == '1') count += 1
      else count -= 1
      if (count == 0) {
        val str = "1" + makeLargestSpecial(s, last + 1, cur) + "0"
        list += str
        last = cur + 1
      }
      cur += 1
    }
    list.sorted(Ordering.String.reverse).mkString
  }
}
