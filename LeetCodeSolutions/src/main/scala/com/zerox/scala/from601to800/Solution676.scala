package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/7/11 9:55
 * @note
 * 676. 实现一个魔法字典 | 难度：中等 | 标签：
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 *
 * 实现 MagicDictionary 类：
 *
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *  
 *
 * 提示：
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-magic-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution676 {
  def main(args: Array[String]): Unit = {
    val dict = new MagicDictionary_Trie2
    dict.buildDict(Array("hello", "leetcode"))
    println(dict.search("hello"))
    println(dict.search("hhllo"))
    println(dict.search("hell"))
    println(dict.search("leetcoded"))

    println("----------------")

    // ["MagicDictionary", "buildDict", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search"]
    // [[], [["a","b","ab","abc","abcabacbababdbadbfaejfoiawfjaojfaojefaowjfoawjfoawj","abcdefghijawefe","aefawoifjowajfowafjeoawjfaow","cba","cas","aaewfawi","babcda","bcd","awefj"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["ab"], ["ba"], ["abc"], ["cba"], ["abb"], ["bb"], ["aa"], ["bbc"], ["abcd"]]
    val dict2 = new MagicDictionary_Trie2
    dict2.buildDict(Array("a", "b", "ab", "abc", "abcabacbababdbadbfaejfoiawfjaojfaojefaowjfoawjfoawj", "abcdefghijawefe",
      "aefawoifjowajfowafjeoawjfaow", "cba", "cas", "aaewfawi", "babcda", "bcd", "awefj"))
    println(dict2.search("a"))
    println(dict2.search("b"))
    println(dict2.search("c"))
    println(dict2.search("d"))
    println(dict2.search("e"))
    println(dict2.search("f"))
    println(dict2.search("ab"))
    println(dict2.search("ba"))
    println(dict2.search("abc"))
    println(dict2.search("cba"))
    println(dict2.search("abb"))
    println(dict2.search("bb"))
    println(dict2.search("aa"))
    println(dict2.search("bbc"))
    println(dict2.search("abcd"))
  }

  /**
   * 执行用时：1292 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：83 / 83
   *
   * 还可以使用字典树优化
   */
  class MagicDictionary() {
    private val set = new scala.collection.mutable.HashSet[String]

    def buildDict(dictionary: Array[String]) {
      set ++= dictionary
    }

    def search(searchWord: String): Boolean = {
      val sb = new StringBuilder(searchWord)
      for (i <- searchWord.indices; j <- 'a' to 'z' if j != searchWord(i)) {
        sb(i) = j
        if (set.contains(sb.toString())) return true
        sb(i) = searchWord(i)
      }
      false
    }

  }

  /**
   * 执行用时：636 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：83 / 83
   */
  class MagicDictionary_Trie() {
    private val trie = new Trie('#', false)

    def buildDict(dictionary: Array[String]) {
      trie.buildDict(dictionary)
    }

    def search(searchWord: String): Boolean = {
      searchWord.indices.exists(trie.jumpExist(searchWord, 0, _))
    }
  }

  class Trie(val char: Char, var end: Boolean) {
    val children = new Array[Trie](26)

    def buildDict(dictionary: Array[String]): Unit = {
      var pre = this
      dictionary.foreach(str => {
        pre = this
        str.foreach(ch => {
          if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie(ch, false)
          pre = pre.children(ch - 'a')
        })
        pre.end = true
      })
    }

    def exist(str: String, from: Int): Boolean = {
      if (children(str(from) - 'a') == null) false
      else if (from == str.length - 1) children(str(from) - 'a').end
      else children(str(from) - 'a').exist(str, from + 1)
    }

    def jumpExist(str: String, from: Int, jump: Int): Boolean = {
      if (from == jump) {
        if (from == str.length - 1) children.exists(t => t != null && t.char != str(from) && t.end)
        else children.exists(t => t != null && t.char != str(from) && t.exist(str, from + 1))
      }
      else if (children(str(from) - 'a') == null) false
      else children(str(from) - 'a').jumpExist(str, from + 1, jump)
    }
  }

  /**
   * 执行用时：656 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：83 / 83
   */
  class MagicDictionary_Trie2() {
    private val trie = new Trie2('#', false)

    def buildDict(dictionary: Array[String]) {
      trie.buildDict(dictionary)
    }

    def search(searchWord: String): Boolean = {
      var index = 0
      var pre = trie
      while (index < searchWord.length) {
        pre.jumpExist(searchWord, index) match {
          case null => return false
          case t if t.char == '*' => return true
          case t => pre = t
        }
        index += 1
      }
      false
    }
  }

  object Trie2 {
    final val FOUND = new Trie2('*', true)
  }

  class Trie2(val char: Char, var end: Boolean) {
    val children = new Array[Trie2](26)

    def buildDict(dictionary: Array[String]): Unit = {
      var pre = this
      dictionary.foreach(str => {
        pre = this
        str.foreach(ch => {
          if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie2(ch, false)
          pre = pre.children(ch - 'a')
        })
        pre.end = true
      })
    }

    def exist(str: String, from: Int): Boolean = {
      if (children(str(from) - 'a') == null) false
      else if (from == str.length - 1) children(str(from) - 'a').end
      else children(str(from) - 'a').exist(str, from + 1)
    }

    def jumpExist(str: String, jump: Int): Trie2 = {
      if (jump == str.length - 1) {
        if (children.exists(t => t != null && t.char != str(jump) && t.end)) Trie2.FOUND else null
      }
      else if (children.exists(t => t != null && t.char != str(jump) && t.exist(str, jump + 1))) Trie2.FOUND
      else children(str(jump) - 'a')
    }
  }

  /**
   * Your MagicDictionary object will be instantiated and called as such:
   * var obj = new MagicDictionary()
   * obj.buildDict(dictionary)
   * var param_2 = obj.search(searchWord)
   */
}
