package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/10/29 16:13
 * @note
 * 186. 反转字符串中的单词 II | 难度：中等 | 标签：双指针、字符串
 * 给你一个字符数组 s ，反转其中 单词 的顺序。
 *
 * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
 *
 * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
 *
 * 示例 1：
 * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 *
 * 示例 2：
 * 输入：s = ["a"]
 * 输出：["a"]
 *
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 可以是一个英文字母（大写或小写）、数字、或是空格 ' ' 。
 * s 中至少存在一个单词
 * s 不含前导或尾随空格
 * 题目数据保证：s 中的每个单词都由单个空格分隔
 */
object Solution186 {
  /**
   * 时间 600 ms 击败 100%
   * 内存 56.9 MB 击败 100%
   *
   * @param s
   */
  def reverseWords(s: Array[Char]): Unit = {
    for (i <- 0 until s.length / 2) {
      val temp = s(i)
      s(i) = s(s.length - 1 - i)
      s(s.length - 1 - i) = temp
    }
    var from = 0
    var to = 1
    while (to < s.length) {
      while (to < s.length && s(to) != ' ') to += 1
      var l = from
      var r = to - 1
      while (l < r) {
        val temp = s(l)
        s(l) = s(r)
        s(r) = temp
        l += 1
        r -= 1
      }
      to += 1
      from = to
    }
  }
}
