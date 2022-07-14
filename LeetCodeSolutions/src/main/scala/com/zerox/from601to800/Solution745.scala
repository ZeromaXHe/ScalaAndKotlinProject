package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/14 10:01
 * @note
 * 745. 前缀和后缀搜索 | 难度：困难 | 标签：设计、字典树、字符串
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 *
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 *
 * 示例：
 *
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 *
 * 提示：
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 104 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution745 {
  def main(args: Array[String]): Unit = {
    println(('z' + 1).toChar)
    println(new WordFilter(Array("apple", "able", "awake")).f("a", "e"))
    println(new WordFilter(Array("abbba", "abba")).f("ab", "ba"))
  }

  /**
   * 执行用时：1900 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：144.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：17 / 17
   *
   * @param _words
   */
  class WordFilter(_words: Array[String]) {
    val trie = new Trie('#', -1)
    for (i <- _words.indices; j <- 0 to _words(i).length) {
      trie.addStr(_words(i), i, j)
    }

    def f(pref: String, suff: String): Int = {
      trie.exist(suff + "{" + pref)
    }
  }

  class Trie(val char: Char, var index: Int) {
    // 'z' + 1 是 '{', 用作前缀后缀的分隔符
    val children = new Array[Trie](27)

    def addStr(str: String, index: Int, suffixFrom: Int): Unit = {
      // 减少 substring 操作
      var pre = this
      for (j <- suffixFrom until str.length) {
        val ch = str(j)
        if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie(ch, index)
        pre = pre.children(ch - 'a')
        pre.index = index
      }
      if (pre.children(26) == null) pre.children(26) = new Trie('{', index)
      pre = pre.children(26)
      pre.index = index
      for (j <- str.indices) {
        val ch = str(j)
        if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie(ch, index)
        pre = pre.children(ch - 'a')
        pre.index = index
      }
    }

    def exist(str: String): Int = {
      var node = this
      for (ch <- str) {
        if (node.children(ch - 'a') == null) return -1
        node = node.children(ch - 'a')
      }
      node.index
    }
  }

  /**
   * 超出时间限制
   *
   * @param _words
   */
  class WordFilter_timeout(_words: Array[String]) {
    val triePrefix = new Trie_timeout('#', false)
    val trieSuffix = new Trie_timeout('#', false)
    triePrefix.buildDict(_words, prefix = true)
    trieSuffix.buildDict(_words, prefix = false)

    def f(pref: String, suff: String): Int = {
      val prefList = triePrefix.getIndexes(pref, 0, pref.length, 1)
      val suffList = trieSuffix.getIndexes(suff, suff.length - 1, -1, -1)
      if (prefList.isEmpty || suffList.isEmpty) return -1
      var prefI = prefList.length - 1
      var suffI = suffList.length - 1
      while (prefI >= 0 && suffI >= 0) {
        if (prefList(prefI) == suffList(suffI)) {
          return prefList(prefI)
        }
        if (prefList(prefI) > suffList(suffI)) prefI -= 1
        else suffI -= 1
      }
      -1
    }
  }

  object Trie_timeout {
    final val EMPTY_LISTBUFFER = scala.collection.mutable.ListBuffer.empty[Int]
  }

  class Trie_timeout(val char: Char, var end: Boolean) {
    val children = new Array[Trie_timeout](26)
    val indexes = new scala.collection.mutable.ListBuffer[Int]

    def buildDict(dictionary: Array[String], prefix: Boolean): Unit = {
      var pre = this
      for (i <- if (prefix) dictionary.indices else dictionary.indices.reverse) {
        val str = dictionary(i)
        pre = this
        for (j <- if (prefix) str.indices else str.indices.reverse) {
          val ch = str(j)
          if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie_timeout(ch, false)
          pre = pre.children(ch - 'a')
          if (prefix) pre.indexes append i
          else pre.indexes prepend i
        }
        pre.end = true
      }
    }

    /**
     * @param str
     * @param from
     * @param to
     * @param step
     * @return 力扣上不能返回 Seq[Int], 自己编译运行是可以的
     */
    @scala.annotation.tailrec
    final def getIndexes(str: String, from: Int, to: Int, step: Int): scala.collection.mutable.ListBuffer[Int] = {
      if (from == to) indexes
      else if (children(str(from) - 'a') == null) Trie_timeout.EMPTY_LISTBUFFER
      else children(str(from) - 'a').getIndexes(str, from + step, to, step)
    }
  }

  /**
   * Your WordFilter object will be instantiated and called as such:
   * var obj = new WordFilter(words)
   * var param_1 = obj.f(pref,suff)
   */
}
