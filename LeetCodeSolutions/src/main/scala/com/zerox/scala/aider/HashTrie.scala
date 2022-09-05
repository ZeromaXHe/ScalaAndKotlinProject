package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 11:28
 * @note
 * 哈希表实现的字典树
 */
class HashTrie {

  import scala.collection.mutable

  private case class TrieNode(var end: Boolean = false,
                              children: mutable.HashMap[Char, TrieNode] = new mutable.HashMap[Char, TrieNode])

  val root: TrieNode = TrieNode()

  def addAll(dictionary: List[String]): Unit = {
    dictionary.foreach(add)
  }

  def add(str: String): Unit = {
    var pre = root
    str.foreach(ch => {
      if (!pre.children.contains(ch)) pre.children(ch) = TrieNode()
      pre = pre.children(ch)
    })
    pre.end = true
  }

  def contains(s: String, from: Int, to: Int): Boolean = {
    var node = root
    for (i <- from until to) {
      if (!node.children.contains(s(i))) return false
      node = node.children(s(i))
    }
    node.end
  }
}
