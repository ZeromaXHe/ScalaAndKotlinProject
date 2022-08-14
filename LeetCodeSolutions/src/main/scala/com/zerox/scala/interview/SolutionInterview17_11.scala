package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/7/11 14:15
 * @note
 * 面试题 17.11. 单词距离 | 难度：中等 | 标签：
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 *
 * words.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-closest-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_11 {
  /**
   * 执行用时：692 ms, 在所有 Scala 提交中击败了 81.82% 的用户
   * 内存消耗：68.1 MB, 在所有 Scala 提交中击败了 27.27% 的用户
   * 通过测试用例：43 / 43
   *
   * @param words
   * @param word1
   * @param word2
   * @return
   */
  def findClosest(words: Array[String], word1: String, word2: String): Int = {
    var i1 = -1
    var i2 = -1
    var min = Int.MaxValue
    for (i <- words.indices) {
      if (words(i) == word1) {
        i1 = i
        if (i2 != -1) min = math.min(i - i2, min)
      } else if (words(i) == word2) {
        i2 = i
        if (i1 != -1) min = math.min(i - i1, min)
      }
    }
    min
  }
}
