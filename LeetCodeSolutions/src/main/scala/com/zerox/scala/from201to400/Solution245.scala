package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 16:46
 * @note
 * 245. 最短单词距离 III | 难度：中等 | 标签：数组、字符串
 * 给定一个字符串数组 wordsDict 和两个字符串 word1 和 word2 ，返回列表中这两个单词之间的最短距离。
 *
 * 注意：word1 和 word2 是有可能相同的，并且它们将分别表示为列表中 两个独立的单词 。
 *
 * 示例 1：
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出：1
 *
 * 示例 2：
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * 输出：3
 *
 * 提示：
 * 1 <= wordsDict.length <= 105
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 都在 wordsDict 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-word-distance-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution245 {
  /**
   * 执行用时：780 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：71 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：47 / 47
   *
   * @param wordsDict
   * @param word1
   * @param word2
   * @return
   */
  def shortestWordDistance(wordsDict: Array[String], word1: String, word2: String): Int = {
    if (word1 == word2) {
      var last = -1
      var shortest = Int.MaxValue
      for (i <- wordsDict.indices) {
        if (wordsDict(i) == word1) {
          if (last != -1) shortest = shortest min (i - last)
          last = i
        }
      }
      shortest
    } else {
      Solution243.shortestDistance(wordsDict, word1, word2)
    }
  }
}
