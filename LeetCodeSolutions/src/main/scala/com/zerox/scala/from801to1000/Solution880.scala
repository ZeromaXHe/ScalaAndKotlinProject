package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/8/29 17:33
 * @note
 * 880. 索引处的解码字符串 | 难度：中等 | 标签：栈、字符串
 * 给定一个编码字符串 S。请你找出 解码字符串 并将其写入磁带。解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
 *
 * 如果所读的字符是字母，则将该字母写在磁带上。
 * 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
 * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
 *
 * 示例 1：
 * 输入：S = "leet2code3", K = 10
 * 输出："o"
 * 解释：
 * 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
 * 字符串中的第 10 个字母是 "o"。
 *
 * 示例 2：
 * 输入：S = "ha22", K = 5
 * 输出："h"
 * 解释：
 * 解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。
 *
 * 示例 3：
 * 输入：S = "a2345678999999999999999", K = 1
 * 输出："a"
 * 解释：
 * 解码后的字符串为 "a" 重复 8301530446056247680 次。第 1 个字母是 "a"。
 *
 * 提示：
 * 2 <= S.length <= 100
 * S 只包含小写字母与数字 2 到 9 。
 * S 以字母开头。
 * 1 <= K <= 10^9
 * 题目保证 K 小于或等于解码字符串的长度。
 * 解码后的字符串保证少于 2^63 个字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decoded-string-at-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution880 {
  def main(args: Array[String]): Unit = {
    println(decodeAtIndex("leet2code3", 10)) // "o"
    println(decodeAtIndex("ha22", 5)) // "h"
    println(decodeAtIndex("ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp", 976159153)) // "a"
  }

  /**
   * 执行用时：536 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：45 / 45
   *
   * @param s
   * @param k
   * @return
   */
  def decodeAtIndex(s: String, k: Int): String = {
    val stack = new scala.collection.mutable.Stack[(Long, Int)]
    var idx = 0
    var count = 0L
    while (count < k) {
      if (Character.isDigit(s(idx))) {
        stack.push((count, idx))
        count *= s(idx) - '0'
      } else {
        count += 1
        if (count == k) return s.substring(idx, idx + 1)
      }
      idx += 1
    }
    var vk = k - 1L
    while (stack.nonEmpty) {
      vk %= stack.head._1
      stack.pop()
      if (stack.nonEmpty) {
        val preCount = stack.head._1 * (s(stack.head._2) - '0')
        if (preCount <= vk) {
          val res = (stack.head._2 + 1 + vk - preCount).toInt
          return s.substring(res, res + 1)
        }
      }
    }
    s.substring(vk.toInt, vk.toInt + 1)
  }
}
