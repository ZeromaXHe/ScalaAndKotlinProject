package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/9 9:43
 * @note
 * 856. 括号的分数 | 难度：中等
 */
object Solution856 {
  /**
   * 执行用时：468 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：86 / 86
   *
   * @param s
   * @return
   */
  def scoreOfParentheses(s: String): Int = {
    var left = 0
    val stack = new scala.collection.mutable.Stack[Int]
    stack.push(0)
    for (c <- s) {
      if (c == '(') {
        left += 1
        stack.push(0)
      } else {
        left -= 1
        val pop = stack.pop()
        val pop2 = stack.pop()
        stack.push(pop2 + (if (pop == 0) 1 else 2 * pop))
      }
    }
    stack.head
  }
}
