package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/9/18 10:33
 * @note
 * 第 311 场周赛
 */
object WeeklyContest311 {
  /**
   * 6180. 最小偶倍数 | 难度：简单
   * 150 / 150 个通过测试用例
   * 状态：通过
   * 执行用时: 404 ms
   * 内存消耗: 51 MB
   *
   * @param n
   * @return
   */
  def smallestEvenMultiple(n: Int): Int = {
    lcm(2, n).toInt
  }

  def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)

  def lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

  /**
   * 6181. 最长的字母序连续子字符串的长度 | 难度：中等
   * 123 / 123 个通过测试用例
   * 状态：通过
   * 执行用时: 528 ms
   * 内存消耗: 53.6 MB
   *
   * @param s
   * @return
   */
  def longestContinuousSubstring(s: String): Int = {
    var max = 1
    var count = 1
    for (i <- 1 until s.length) {
      if (s(i) - 'a' != s(i - 1) - 'a' + 1) {
        count = 1
      } else {
        count += 1
        if (count > max) max = count
      }
    }
    max
  }

  /**
   * 6182. 反转二叉树的奇数层 | 难度：中等
   * 57 / 57 个通过测试用例
   * 状态：通过
   * 执行用时: 8088 ms
   * 内存消耗: 63.9 MB
   *
   * @param root
   * @return
   */
  def reverseOddLevels(root: TreeNode): TreeNode = {
    import scala.collection.mutable
    val queue = new mutable.Queue[TreeNode]
    var depth = 1
    queue.enqueue(root)
    val stack = new mutable.Stack[Int]
    val q = new mutable.Queue[TreeNode]
    while (queue.nonEmpty) {
      var size = queue.size
      while (size > 0) {
        val node = queue.dequeue()
        if (node.left != null) queue.enqueue(node.left)
        if (node.right != null) queue.enqueue(node.right)
        if (depth % 2 == 0) {
          q.enqueue(node)
          stack.push(node.value)
        }
        size -= 1
      }
      while (q.nonEmpty) {
        q.dequeue().value = stack.pop()
      }
      depth += 1
    }
    root
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  /**
   * 6183. 字符串的前缀分数和 | 难度：困难
   * 38 / 38 个通过测试用例
   * 状态：通过
   * 执行用时: 1264 ms
   * 内存消耗: 148.7 MB
   *
   * @param words
   * @return
   */
  def sumPrefixScores(words: Array[String]): Array[Int] = {
    val trie = new ArrayTrie()
    trie.addAll(words)
    words.map(trie.preCountSum(_) - 1)
  }

  class ArrayTrie {
    case class TrieNode(char: Char,
                        var count: Int = 0,
                        children: Array[TrieNode] = new Array[TrieNode](26))

    val root: TrieNode = TrieNode('#')

    def addAll(dictionary: Seq[String]): Unit = {
      dictionary.foreach(add)
    }

    def add(str: String): Unit = {
      var pre = root
      str.foreach(ch => {
        if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = TrieNode(ch)
        pre = pre.children(ch - 'a')
        pre.count += 1
      })
    }

    def preCountSum(s: String): Int = {
      var node = root
      var sum = 0
      for (ch <- s) {
        if (node.children(ch - 'a') == null) return sum
        node = node.children(ch - 'a')
        sum += node.count
      }
      sum
    }
  }
}
