package com.zerox.scala.from201to400

import com.zerox.scala.aider.ArrayTrie

/**
 * @author zhuxi
 * @since 2022/9/13 16:03
 * @note
 * 336. 回文对 | 难度：困难 | 标签：字典树、数组、哈希表、字符串
 * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1：
 * 输入：words = ["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * 示例 2：
 * 输入：words = ["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 * 示例 3：
 * 输入：words = ["a",""]
 * 输出：[[0,1],[1,0]]
 *
 * 提示：
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution336 {
  def main(args: Array[String]): Unit = {
    // println(isPalindromeSuffix("llsss", 3))
    println(palindromePairs(Array("abcd", "dcba", "lls", "s", "sssll")))
    println(palindromePairs(Array("a", "")))
  }

  def palindromePairs_timeout(words: Array[String]): List[List[Int]] = {
    val trie = new ArrayTrie
    trie.addAll(words)
    val revTrie = new ArrayTrie
    val revs = words.map(_.reverse)
    revTrie.addAll(revs)
    val map = words.zipWithIndex.toMap
    val res = new scala.collection.mutable.HashSet[List[Int]]
    for (w <- words) {
      val i1 = map(w)
      val pres = revTrie.existPres(w)
      for (pre <- pres if isPalindromeSuffix(w, pre)) {
        val i2 = map(w.substring(0, pre).reverse)
        if (i1 != i2) res.addOne(List(i1, i2))
      }
    }
    for (r <- revs) {
      val i1 = map(r.reverse)
      val pres = trie.existPres(r)
      for (pre <- pres if isPalindromeSuffix(r, pre)) {
        val i2 = map(r.substring(0, pre))
        if (i1 != i2) res.addOne(List(i2, i1))
      }
    }
    res.toList
  }

  def isPalindromeSuffix(s: String, idx: Int): Boolean = {
    for (i <- idx until (s.length + idx) / 2 if s(i) != s(s.length - 1 - i + idx)) {
      return false
    }
    true
  }

  /**
   * 执行用时：5252 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：79.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：136 / 136
   *
   * @param words
   * @return
   */
  def palindromePairs(words: Array[String]): List[List[Int]] = {
    val map = words.map(_.reverse).zipWithIndex.toMap
    (for (i <- words.indices;
          w = words(i);
          m = w.length if m > 0;
          j <- 0 to m if isPalindrome(w, j, m);
          sub = w.substring(0, j) if map.contains(sub) && map(sub) != i) yield {
      List(i, map(sub))
    }).toList :::
      (for (i <- words.indices;
            w = words(i);
            m = w.length if m > 0;
            j <- 0 to m if j != 0 && isPalindrome(w, 0, j);
            sub = w.substring(j, m) if map.contains(sub) && map(sub) != i) yield {
        List(map(sub), i)
      }).toList
  }


  def isPalindrome(s: String, from: Int, to: Int): Boolean = {
    for (i <- from until from + (to - from) / 2 if s(i) != s(to - 1 - i + from)) {
      return false
    }
    true
  }
}
