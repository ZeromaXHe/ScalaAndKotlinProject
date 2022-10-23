package com.zerox.scala.from1601to1800

/**
 * @author ZeromaXHe
 * @since 2022/10/23 16:44
 * @note
 * 1768. 交替合并字符串 | 难度：简单
 */
object Solution1768 {
  /**
   * 时间 488 ms 击败 50%
   * 内存 52.2 MB 击败 50%
   *
   * @param word1
   * @param word2
   * @return
   */
  def mergeAlternately(word1: String, word2: String): String = {
    val stringBuilder = (word1 zip word2).foldLeft(new StringBuilder)((sb, t) => sb.append(t._1).append(t._2))
    if (word1.length > word2.length) stringBuilder.append(word1.substring(word2.length))
    else if (word2.length > word1.length) stringBuilder.append(word2.substring(word1.length))
    stringBuilder.toString()
  }
}
