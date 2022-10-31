package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/10/31 9:51
 * @note
 * 481. 神奇字符串 | 难度：中等
 */
object Solution481 {
  val treeMap = new scala.collection.mutable.TreeMap[Int, Int]
  treeMap(1) = 1
  val queue = new scala.collection.mutable.Queue[Int]
  queue.enqueue(2)
  var next1 = true
  var cur = 3
  var count = 3
  while (count <= 100000) {
    val deq = queue.dequeue()
    val next = if (next1) 1 else 2
    if (deq == 1) {
      queue.enqueue(next)
      count += 1
      treeMap(cur) = treeMap.size + 1
    } else {
      queue.enqueue(next)
      count += 1
      queue.enqueue(next)
      count += 1
    }
    cur += 1
    next1 = !next1
  }
  while (queue.nonEmpty) {
    val deq = queue.dequeue()
    if (deq == 1) treeMap(cur) = treeMap.size + 1
    cur += 1
  }

  /**
   * 执行用时：688 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：64 / 64
   *
   * @param n
   * @return
   */
  def magicalString(n: Int): Int = {
    treeMap.maxBefore(n + 1).get._2
  }
}
