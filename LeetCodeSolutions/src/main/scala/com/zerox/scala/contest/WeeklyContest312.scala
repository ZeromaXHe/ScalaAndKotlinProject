package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/9/25 10:25
 * @note
 * 第 312 场周赛
 */
object WeeklyContest312 {
  /**
   * 6188. 按身高排序 | 难度：简单
   * 68 / 68 个通过测试用例
   * 状态：通过
   * 执行用时: 576 ms
   * 内存消耗: 53.9 MB
   *
   * @param names
   * @param heights
   * @return
   */
  def sortPeople(names: Array[String], heights: Array[Int]): Array[String] = {
    (names zip heights).sortBy(-_._2).map(_._1)
  }

  /**
   * 6189. 按位与最大的最长子数组 | 难度：中等
   * 50 / 50 个通过测试用例
   * 状态：通过
   * 执行用时: 816 ms
   * 内存消耗: 76.3 MB
   *
   * @param nums
   * @return
   */
  def longestSubarray(nums: Array[Int]): Int = {
    val maxBit = nums.max
    var max = 1
    var count = 0
    for (num <- nums) {
      if (num == maxBit) count += 1
      else {
        if (count > max) max = count
        count = 0
      }
    }
    max max count
  }

  /**
   * 6190. 找到所有好下标 | 难度：中等
   * 66 / 66 个通过测试用例
   * 状态：通过
   * 执行用时: 976 ms
   * 内存消耗: 83 MB
   *
   * @param nums
   * @param k
   * @return
   */
  def goodIndices(nums: Array[Int], k: Int): List[Int] = {
    if (nums.length <= 2 * k) return List.empty
    if (k == 1) return (1 until nums.length - 1).toList
    val pre = new scala.collection.mutable.Queue[Int]
    val pos = new scala.collection.mutable.Queue[Int]
    var preCount = 0
    var posCount = 0
    for (i <- 0 until k - 1) {
      val cmp = nums(i).compare(nums(i + 1))
      pre.enqueue(cmp)
      if (cmp == -1) preCount += 1
    }
    var p1 = k
    var p2 = k + 1
    while (p2 < 2 * k) {
      val cmp = nums(p2).compare(nums(p2 + 1))
      pos.enqueue(cmp)
      if (cmp == 1) posCount += 1
      p2 += 1
    }
    val res = new scala.collection.mutable.ListBuffer[Int]
    if (preCount == 0 && posCount == 0) {
      res.addOne(p1)
    }
    while (p2 < nums.length - 1) {
      if (pre.dequeue() == -1) preCount -= 1
      val cmp1 = nums(p1 - 1).compare(nums(p1))
      pre.enqueue(cmp1)
      if (cmp1 == -1) preCount += 1
      if (pos.dequeue() == 1) posCount -= 1
      val cmp = nums(p2).compare(nums(p2 + 1))
      pos.enqueue(cmp)
      if (cmp == 1) posCount += 1
      p1 += 1
      p2 += 1
      if (preCount == 0 && posCount == 0) {
        res.addOne(p1)
      }
    }
    res.toList
  }

  def numberOfGoodPaths_timeout(vals: Array[Int], edges: Array[Array[Int]]): Int = {
    import scala.collection.mutable
    val visit = new Array[Boolean](vals.length)
    val map = new mutable.HashMap[Int, mutable.HashSet[Int]]
    val in = new Array[Int](vals.length)
    for (e <- edges) {
      if (!map.contains(e(0))) map(e(0)) = new mutable.HashSet[Int]
      map(e(0)) += e(1)
      in(e(1)) += 1
      if (!map.contains(e(1))) map(e(1)) = new mutable.HashSet[Int]
      map(e(1)) += e(0)
      in(e(0)) += 1
    }
    val left = new mutable.HashMap[Int, Int]
    for (v <- vals) {
      if (left.contains(v)) left(v) += 1
      else left(v) = 1
    }

    var count = vals.length

    def dfs(idx: Int, fromVal: Int): Unit = {
      visit(idx) = true
      if (map.contains(idx)) {
        for (next <- map(idx) if !visit(next)) {
          if (vals(next) < fromVal) dfs(next, fromVal)
          else if (vals(next) == fromVal) {
            count += 1
            dfs(next, fromVal)
          }
        }
      }
      visit(idx) = false
    }

    // 拓扑排序
    val queue = new mutable.Queue[Int]
    for (i <- in.indices if in(i) == 1) queue.enqueue(i)
    while (queue.nonEmpty) {
      val size = queue.size
      for (_ <- 0 until size) {
        val deq = queue.dequeue()
        if (left(vals(deq)) > 1) dfs(deq, vals(deq))
        left(vals(deq)) -= 1
        visit(deq) = true
        if (map.contains(deq)) {
          for (v <- map(deq)) {
            in(v) -= 1
            if (in(v) == 1) queue.enqueue(v)
          }
        }
      }
    }
    count
  }

  /**
   * 6191. 好路径的数目 | 难度：困难
   * 没做出来
   *
   * @param vals
   * @param edges
   * @return
   */
  def numberOfGoodPaths(vals: Array[Int], edges: Array[Array[Int]]): Int = {
    import scala.collection.mutable
    val visit = new Array[Boolean](vals.length)
    val map = new mutable.HashMap[Int, mutable.HashSet[Int]]
    val in = new Array[Int](vals.length)
    for (e <- edges) {
      if (!map.contains(e(0))) map(e(0)) = new mutable.HashSet[Int]
      map(e(0)) += e(1)
      in(e(1)) += 1
      if (!map.contains(e(1))) map(e(1)) = new mutable.HashSet[Int]
      map(e(1)) += e(0)
      in(e(0)) += 1
    }

    var count = vals.length

    // 拓扑排序
    val queue = new mutable.Queue[(Int, mutable.HashMap[Int, Int])]
    for (i <- in.indices if in(i) == 1) {
      val cMap = new mutable.HashMap[Int, Int]
      cMap(vals(i)) = 1
      queue.enqueue((i, cMap))
    }
    while (queue.nonEmpty) {
      val size = queue.size
      for (_ <- 0 until size) {
        val (deq, countMap) = queue.dequeue()
        visit(deq) = true
        if (map.contains(deq)) {
          for (next <- map(deq)) {
            in(next) -= 1
            if (in(next) == 1) {
              val newMap = new mutable.HashMap[Int, Int]
              for ((elem, c) <- countMap if elem >= vals(next)) {
                if (elem == vals(next)) count += c
              }
              if (newMap.contains(vals(next))) newMap(vals(next)) += 1
              else newMap(vals(next)) = 1
              queue.enqueue((next, newMap))
            }
          }
        }
      }
    }
    count
  }

  def main(args: Array[String]): Unit = {
    // println(longestSubarray(Array(96317, 96317, 96317, 96317, 96317, 96317, 96317, 96317, 96317, 279979))) // 1
    // println(1.compare(2)) // -1
    // println(3.compare(2)) // 1
    // println(2.compare(2)) // 0
    // println(goodIndices(Array(878724, 201541, 179099, 98437, 35765, 327555, 475851, 598885, 849470, 943442), 4))
    println(numberOfGoodPaths(Array(1, 3, 2, 1, 3), Array(Array(0, 1), Array(0, 2), Array(2, 3), Array(2, 4)))) // 6
    println(numberOfGoodPaths(Array(2, 5, 5, 1, 5, 2, 3, 5, 1, 5),
      Array(Array(0, 1), Array(2, 1), Array(3, 2), Array(3, 4), Array(3, 5), Array(5, 6), Array(1, 7), Array(8, 4), Array(9, 7)))) // 20
  }
}
