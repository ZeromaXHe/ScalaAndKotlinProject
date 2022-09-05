package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 11:13
 * @note
 * 数组实现的字典树，仅支持字符串全部为小写字母或全部为大写字母的情况
 */
class ArrayTrie(lowerCase: Boolean = true) {
  private case class TrieNode(char: Char,
                              var end: Boolean = false,
                              children: Array[TrieNode] = new Array[TrieNode](26))

  val root: TrieNode = TrieNode('#')
  val baseChar: Char = if (lowerCase) 'a' else 'A'

  def addAll(dictionary: List[String]): Unit = {
    dictionary.foreach(add)
  }

  def add(str: String): Unit = {
    var pre = root
    str.foreach(ch => {
      if (pre.children(ch - baseChar) == null) pre.children(ch - baseChar) = TrieNode(ch)
      pre = pre.children(ch - baseChar)
    })
    pre.end = true
  }

  def contains(s: String, from: Int, to: Int): Boolean = {
    var node = root
    for (i <- from until to) {
      if (node.children(s(i) - baseChar) == null) return false
      node = node.children(s(i) - baseChar)
    }
    node.end
  }
}
