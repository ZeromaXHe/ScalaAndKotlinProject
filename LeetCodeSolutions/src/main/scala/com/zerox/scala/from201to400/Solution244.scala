package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 16:29
 * @note
 * 244. 最短单词距离 II | 难度：中等 | 标签：设计、数组、哈希表、双指针、字符串
 * 请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词，并返回列表中这两个单词之间的最短距离。
 *
 * 实现 WordDistanc 类:
 *
 * WordDistance(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 worddict 中 word1 和 word2 之间的最短距离。
 *
 * 示例 1:
 * 输入:
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * 输出:
 * [null, 3, 1]
 *
 * 解释：
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // 返回 3
 * wordDistance.shortest("makes", "coding");    // 返回 1
 *
 * 注意:
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在数组 wordsDict 中
 * word1 != word2
 * shortest 操作次数不大于 5000
 */
object Solution244 {

  /**
   * 时间 648 ms 击败 100%
   * 内存 65.4 MB 击败 100%
   *
   * @param _wordsDict
   */
  class WordDistance(_wordsDict: Array[String]) {
    val map: Map[String, IndexedSeq[Int]] = _wordsDict.indices.groupBy(_wordsDict(_))

    def shortest(word1: String, word2: String): Int = {
      val seq1 = map(word1)
      val seq2 = map(word2)
      var p1 = 0
      var p2 = 0
      var res = Int.MaxValue
      while (p1 < seq1.length && p2 < seq2.length) {
        res = res min (seq1(p1) - seq2(p2)).abs
        if (seq1(p1) < seq2(p2)) {
          p1 += 1
        } else {
          p2 += 1
        }
      }
      res
    }

  }

  /**
   * 时间 1928 ms 击败 100%
   * 内存 62.2 MB 击败 100%
   *
   * @param _wordsDict
   */
  class WordDistance_slow(_wordsDict: Array[String]) {

    def shortest(word1: String, word2: String): Int = {
      Solution243.shortestDistance(_wordsDict, word1, word2)
    }

  }

  /**
   * Your WordDistance object will be instantiated and called as such:
   * var obj = new WordDistance(wordsDict)
   * var param_1 = obj.shortest(word1,word2)
   */
}
