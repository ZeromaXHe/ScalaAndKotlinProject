package com.zerox

/**
 * @author ZeromaXHe
 * @since 2022/6/23 22:30
 * @note
 * 30. 串联所有单词的子串 | 难度：困难 | 标签：哈希表、字符串、滑动窗口
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution30 {
  def main(args: Array[String]): Unit = {
    println(findSubstring("barfoothefoobarman", Array("foo", "bar")) == List(0, 9))
    println(findSubstring("wordgoodgoodgoodbestword", Array("word", "good", "best", "good")) == List(8))
  }

  /**
   * 执行用时：744 ms, 在所有 Scala 提交中击败了 72.73% 的用户
   * 内存消耗：54.5 MB, 在所有 Scala 提交中击败了 63.64% 的用户
   * 通过测试用例：177 / 177
   *
   * @param s
   * @param words
   * @return
   */
  def findSubstring(s: String, words: Array[String]): List[Int] = {
    val map = words.groupBy(identity).mapValues(_.length)
    val wordLen = words(0).length
    val total = words.length * wordLen
    val temp = new collection.mutable.HashMap[String, Int]
    val result = for (i <- 0 to (s.length - total)
                      if slidingMatch(i, s, temp, map, words, wordLen)
                      ) yield i
    result.toList
  }

  /**
   *
   * @param i
   * @param s
   * @param temp
   * @param map 力扣里面的类型得写成 scala.collection.MapView[String, Int]
   * @param words
   * @param wordLen
   * @return
   */
  private def slidingMatch(i: Int, s: String, temp: collection.mutable.HashMap[String, Int],
                      map: Map[String, Int], words: Array[String], wordLen: Int): Boolean = {
    temp.clear()
    for (j <- words.indices) {
      val testStr = s.substring(i + j * wordLen, i + (j + 1) * wordLen)
      if ((map contains testStr) && (!(temp contains testStr) || temp(testStr) < map(testStr))) {
        temp(testStr) = temp.getOrElse(testStr, 0) + 1
      } else {
        return false
      }
    }
    true
  }
}
