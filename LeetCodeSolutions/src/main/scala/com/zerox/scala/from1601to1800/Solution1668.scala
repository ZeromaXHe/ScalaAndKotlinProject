package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/11/3 9:49
 * @note
 * 1668. 最大重复子字符串 | 难度：简单
 */
object Solution1668 {
  /**
   * 执行用时：476 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：212 / 212
   *
   * @param sequence
   * @param word
   * @return
   */
  def maxRepeating(sequence: String, word: String): Int = {
    val dp = new Array[Int](sequence.length)
    var pre = sequence.indexOf(word)
    if (pre == -1) return 0
    dp(pre) = 1
    var max = 1
    var idx = sequence.indexOf(word, pre + 1)
    while (idx != -1) {
      dp(idx) = if (idx >= word.length) dp(idx - word.length) + 1 else 1
      if (dp(idx) > max) max = dp(idx)
      pre = idx
      idx = sequence.indexOf(word, pre + 1)
    }
    max
  }
}
