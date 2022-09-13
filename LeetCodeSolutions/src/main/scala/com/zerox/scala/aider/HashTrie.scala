package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 11:28
 * @note
 * 哈希表实现的字典树
 */
class HashTrie {

  import scala.collection.mutable

  case class TrieNode(var end: Boolean = false,
                      children: mutable.HashMap[Char, TrieNode] = new mutable.HashMap[Char, TrieNode])

  val root: TrieNode = TrieNode()

  def addAll(dictionary: Seq[String]): Unit = {
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

  def contains(s: String): Boolean = contains(s, 0, s.length)

  def contains(s: String, from: Int = 0, to: Int): Boolean = existPres(s, from, to).contains(to)

  def existPres(s: String): Set[Int] = existPres(s, 0, s.length)

  def existPres(s: String, from: Int, to: Int): Set[Int] = {
    var node = root
    val set = new scala.collection.mutable.HashSet[Int]
    if (node.end) set.add(0)
    for (i <- from until to) {
      if (!node.children.contains(s(i))) return set.toSet
      node = node.children(s(i))
      if (node.end) set.add(i + 1)
    }
    set.toSet
  }
}
