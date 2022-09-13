package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 11:13
 * @note
 * 数组实现的字典树，仅支持字符串全部为小写字母或全部为大写字母的情况
 */
class ArrayTrie(lowerCase: Boolean = true) {
  case class TrieNode(char: Char,
                      var end: Boolean = false,
                      children: Array[TrieNode] = new Array[TrieNode](26))

  val root: TrieNode = TrieNode('#')
  val baseChar: Char = if (lowerCase) 'a' else 'A'

  def addAll(dictionary: Seq[String]): Unit = {
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

  def contains(s: String): Boolean = contains(s, 0, s.length)

  def contains(s: String, from: Int = 0, to: Int): Boolean = existPres(s, from, to).contains(to)

  def existPres(s: String): Set[Int] = existPres(s, 0, s.length)

  def existPres(s: String, from: Int = 0, to: Int): Set[Int] = {
    var node = root
    val set = new scala.collection.mutable.HashSet[Int]
    if (node.end) set.add(0)
    for (i <- from until to) {
      if (node.children(s(i) - baseChar) == null) return set.toSet
      node = node.children(s(i) - baseChar)
      if (node.end) set.add(i + 1)
    }
    set.toSet
  }
}
