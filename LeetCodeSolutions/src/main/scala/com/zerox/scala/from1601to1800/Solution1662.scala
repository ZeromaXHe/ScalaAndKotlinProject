package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/11/1 14:22
 * @note
 * 1662. 检查两个字符串数组是否相等 | 难度：简单
 */
object Solution1662 {
  /**
   * 执行用时：440 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：110 / 110
   *
   * @param word1
   * @param word2
   * @return
   */
  def arrayStringsAreEqual(word1: Array[String], word2: Array[String]): Boolean = {
    val str1 = word1.foldLeft(new StringBuilder)(_ append _).toString()
    val str2 = word2.foldLeft(new StringBuilder)(_ append _).toString()
    str1 == str2
  }
}
