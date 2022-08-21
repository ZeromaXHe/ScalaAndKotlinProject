package com.zerox.scala.from1201to1400

/**
 * @author ZeromaXHe
 * @since 2022/8/21 20:31
 * @note
 * 1249. 移除无效的括号 | 难度：中等 | 难度：栈、字符串
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *
 * 示例 1：
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 *
 * 示例 2：
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 *
 * 示例 3：
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 *
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 可能是 '('、')' 或英文小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1249 {
  /**
   * 执行用时：608 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：62 / 62
   *
   * @param s
   * @return
   */
  def minRemoveToMakeValid(s: String): String = {
    val stack = new scala.collection.mutable.Stack[Int]
    val set = new scala.collection.mutable.HashSet[Int]
    for (i <- s.indices) {
      if (s(i) == '(') stack.push(i)
      else if (s(i) == ')') if (stack.isEmpty) set.add(i) else stack.pop()
    }
    if (stack.nonEmpty) stack.foreach(set.add)
    val sb = new StringBuilder
    for (i <- s.indices) {
      if (!set.contains(i)) sb.append(s(i))
    }
    sb.toString()
  }
}
