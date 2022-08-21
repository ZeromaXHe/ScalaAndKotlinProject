package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/8/21 9:49
 * @note
 * 第 307 场周赛
 */
object WeeklyContest307 {
  /**
   * 6152. 赢得比赛需要的最少训练时长 | 难度：简单
   * 107 / 107 个通过测试用例
   * 状态：通过
   * 执行用时: 476 ms
   * 内存消耗: 52.9 MB
   *
   * @param initialEnergy
   * @param initialExperience
   * @param energy
   * @param experience
   * @return
   */
  def minNumberOfHours(initialEnergy: Int, initialExperience: Int, energy: Array[Int], experience: Array[Int]): Int = {
    var exp = initialExperience
    var eng = initialEnergy
    var train = 0
    for (i <- experience.indices) {
      if (eng <= energy(i)) {
        train += energy(i) - eng + 1
        eng = energy(i) + 1
      }
      if (exp <= experience(i)) {
        train += experience(i) - exp + 1
        exp = experience(i) + 1
      }
      exp += experience(i)
      eng -= energy(i)
    }
    train
  }

  /**
   * 6166. 最大回文数字 | 难度：中等
   * 57 / 57 个通过测试用例
   * 状态：通过
   * 执行用时: 732 ms
   * 内存消耗: 56.7 MB
   *
   * @param num
   * @return
   */
  def largestPalindromic(num: String): String = {
    val (map0, map) = num.groupBy(identity).mapValues(_.length).partition(_._1 == '0')
    if (map.isEmpty) return "0"
    val (more1, only1) = map.partition(_._2 > 1)
    val sb = new StringBuilder
    if (more1.isEmpty) return only1.keys.max.toString
    var single = -1
    more1.foreach(t => {
      if (t._2 % 2 == 1) single = math.max(single, t._1 - '0')
      sb.append(t._1.toString * (t._2 / 2))
    })
    if (map0.nonEmpty) {
      if (map0('0') % 2 == 1) single = math.max(single, 0)
      if (map0('0') > 1) sb.append("0" * (map0('0') / 2))
    }
    if (only1.nonEmpty) single = math.max(single, only1.keys.max - '0')
    val sorted = sb.sorted.reverse
    sorted.toString() + (if (single != -1) single.toString else "") + sorted.reverse.toString()
  }

  /**
   * Definition for a binary tree node.
   * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * var value: Int = _value
   * var left: TreeNode = _left
   * var right: TreeNode = _right
   * }
   *
   * 6154. 感染二叉树需要的总时间 | 难度：中等
   * 79 / 79 个通过测试用例
   * 状态：通过
   * 执行用时: 1100 ms
   * 内存消耗: 86.4 MB
   */
  def amountOfTime(root: TreeNode, start: Int): Int = {
    class PrevTreeNode(_node: TreeNode = null, _prev: PrevTreeNode = null) {
      val node: TreeNode = _node
      val prev: PrevTreeNode = _prev
      var left: PrevTreeNode = null
      var right: PrevTreeNode = null
    }
    val queue = new scala.collection.mutable.Queue[PrevTreeNode]
    val rootNode = new PrevTreeNode(root)
    queue.enqueue(rootNode)
    var startNode: PrevTreeNode = null
    while (queue.nonEmpty) {
      var c = queue.size
      while (c > 0) {
        val node = queue.dequeue()
        if (node.node.value == start) {
          startNode = node
        }
        if (node.node.left != null) {
          val left = new PrevTreeNode(node.node.left, node)
          node.left = left
          queue.enqueue(left)
        }
        if (node.node.right != null) {
          val right = new PrevTreeNode(node.node.right, node)
          node.right = right
          queue.enqueue(right)
        }
        c -= 1
      }
    }
    queue.enqueue(startNode)
    var result = -1
    val set = new scala.collection.mutable.HashSet[Int]
    while (queue.nonEmpty) {
      var c = queue.size
      while (c > 0) {
        val node = queue.dequeue()
        set.add(node.node.value)
        if (node.left != null && !set(node.left.node.value)) queue.enqueue(node.left)
        if (node.right != null && !set(node.right.node.value)) queue.enqueue(node.right)
        if (node.prev != null && !set(node.prev.node.value)) queue.enqueue(node.prev)
        c -= 1
      }
      result += 1
    }
    result
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  /**
   * 6155. 找出数组的第 K 大和 | 难度：困难
   * 111 / 111 个通过测试用例
   * 状态：通过
   * 执行用时: 1040 ms
   * 内存消耗: 75.6 MB
   *
   * 比赛结束才做出来的…… 要不然就有机会前 200 名了，伤心
   *
   * @param nums
   * @param k
   * @return
   */
  def kSum(nums: Array[Int], k: Int): Long = {
    var sum = 0L
    for (num <- nums if num > 0) {
      sum += num
    }
    if (k == 1) return sum
    val sorted = nums.map(math.abs).sorted
    // 小顶堆
    val heap = new scala.collection.mutable.PriorityQueue[(Long, Int)]()(Ordering.by[(Long, Int), Long](_._1).reverse)
    heap.enqueue((sorted(0), 0))
    for (i <- 2 until k) {
      val deq = heap.dequeue()
      if (deq._2 + 1 < sorted.length) {
        heap.enqueue((deq._1 + sorted(deq._2 + 1), deq._2 + 1))
        heap.enqueue((deq._1 - sorted(deq._2) + sorted(deq._2 + 1), deq._2 + 1))
      }
    }
    sum - heap.head._1
  }

  def main(args: Array[String]): Unit = {
    //    println(largestPalindromic("444947137"))
    //    println(largestPalindromic("00009"))
    //    println(largestPalindromic("00001105"))
    //    println(1 << 10)
    println(kSum(Array(2, 4, -2), 5)) // 2
    println(kSum(Array(1, -2, 3, 4, -10, 12), 16)) // 10
  }
}
