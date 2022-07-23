package com.zerox.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/23 22:05
 * @note
 * 第 83 场双周赛
 */
object BiweeklyContest83 {
  /**
   * 6128. 最好的扑克手牌 | 难度：简单
   *
   * @param ranks
   * @param suits
   * @return
   */
  def bestHand(ranks: Array[Int], suits: Array[Char]): String = {
    if (suits(0) == suits(1) && suits(0) == suits(2) && suits(0) == suits(3) && suits(0) == suits(4)) {
      "Flush";
    } else {
      ranks.groupBy(identity).mapValues(_.length).values.max match {
        case max if max >= 3 => "Three of a Kind"
        case 2 => "Pair"
        case _ => "High Card"
      }
    }
  }

  /**
   * 6129. 全 0 子数组的数目 | 难度：中等
   *
   * @param nums
   * @return
   */
  def zeroFilledSubarray(nums: Array[Int]): Long = {
    var connect0 = 0L
    var sum = 0L
    for (num <- nums) {
      if (num == 0) {
        connect0 += 1
      } else {
        if (connect0 > 0) {
          sum += (connect0 + 1) * connect0 / 2
        }
        connect0 = 0
      }
    }
    if (connect0 > 0) {
      sum += (connect0 + 1) * connect0 / 2
    }
    sum
  }

  /**
   * 6130. 设计数字容器系统 | 难度：中等
   */
  class NumberContainers() {
    private val numberToIndex = new scala.collection.mutable.HashMap[Int, java.util.PriorityQueue[Int]]
    private val indexToNumber = new scala.collection.mutable.HashMap[Int, Int]

    def change(index: Int, number: Int) {
      if (indexToNumber.contains(index)) {
        val indexHeap = numberToIndex(indexToNumber(index))
        indexHeap.remove(index)
        if (indexHeap.isEmpty) {
          numberToIndex.remove(indexToNumber(index))
        }
      }
      indexToNumber(index) = number
      if (!numberToIndex.contains(number)) {
        numberToIndex(number) = new java.util.PriorityQueue[Int]
      }
      numberToIndex(number).add(index)
    }

    def find(number: Int): Int = {
      if (!numberToIndex.contains(number)) {
        -1
      } else {
        numberToIndex(number).peek()
      }
    }

  }

  /**
   * Your NumberContainers object will be instantiated and called as such:
   * var obj = new NumberContainers()
   * obj.change(index,number)
   * var param_2 = obj.find(number)
   */

  def shortestSequence_backup(rolls: Array[Int], k: Int): Int = {
    if (k > rolls.length) return 1
    val root = new Sequence(k, null, -1)
    var deep = 1
    val queue = new scala.collection.mutable.Queue[Sequence]
    var countToBe = 1 + k
    var layerCount = k
    queue += root
    while (true) {
      for (i <- rolls.indices) {
        val r = rolls(i)
        for (seq <- queue) {
          if (seq.firstIndex < i && seq.next(r - 1) == null) {
            seq.next(r - 1) = new Sequence(k, seq, i)
            seq.count += 1
            var parent = seq.parent
            while (parent != null) {
              parent.count += 1
              parent = parent.parent
            }
          }
        }
      }
      if (root.count != countToBe) {
        return deep
      }
      deep += 1
      var delCount = queue.size
      while (delCount > 0) {
        val deq = queue.dequeue()
        deq.next.foreach(queue enqueue _)
        delCount -= 1
      }
      countToBe += k * layerCount
      layerCount *= k
    }
    deep
  }

  class Sequence(val k: Int, val parent: Sequence, val firstIndex: Int) {
    var count = 1
    val next = new Array[Sequence](k)
  }

  /**
   * 6131. 不可能得到的最短骰子序列 | 难度：困难
   *
   * @param rolls
   * @param k
   * @return
   */
  def shortestSequence(rolls: Array[Int], k: Int): Int = {
    if (k > rolls.length) return 1
    val from = Array.fill(k)(-1)
    var newFrom = 0
    var deep = 1
    while (true) {
      for (i <- newFrom until (rolls.length)) {
        if (from(rolls(i) - 1) < newFrom) {
          from(rolls(i) - 1) = i
        }
      }
      if (from.exists(_ < newFrom)) {
        return deep
      }
      newFrom = from.max + 1
      deep += 1
    }
    deep
  }

  def main(args: Array[String]): Unit = {
    println(shortestSequence(Array(4, 2, 1, 2, 3, 3, 2, 4, 1), 4))
    println(shortestSequence(Array(1, 1, 2, 2), 2))
    println(shortestSequence(Array(1, 1, 3, 2, 2, 2, 3, 3), 4))
    println(shortestSequence(Array(2, 1, 4, 2, 1, 1, 2, 2, 2, 3, 2, 1, 4, 2, 4, 2, 2, 1, 1, 4, 2, 4, 3, 2, 3, 4, 1, 3,
      4, 2, 1, 1, 2, 3, 1, 4, 2, 2, 3, 4, 1, 2, 1, 1, 1, 1, 1, 4, 3, 2, 3, 4, 1, 4, 1, 3, 3, 2, 1, 4, 3, 4, 2, 3, 2), 4))
  }

  private def test3(): Unit = {
    val containers = new NumberContainers
    println(containers.find(10))
    containers.change(2, 10)
    containers.change(1, 10)
    containers.change(3, 10)
    containers.change(5, 10)
    println(containers.find(10))
    containers.change(1, 20)
    println(containers.find(10))
  }
}
