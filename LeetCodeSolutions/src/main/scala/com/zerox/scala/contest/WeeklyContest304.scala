package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/31 10:19
 * @note
 * 第 304 场周赛
 */
object WeeklyContest304 {
  /**
   * 6132. 使数组中所有元素都等于零 | 难度：简单
   * 95 / 95 个通过测试用例
   * 状态：通过
   * 执行用时: 428 ms
   * 内存消耗: 52.8 MB
   *
   * @param nums
   * @return
   */
  def minimumOperations(nums: Array[Int]): Int = {
    nums.filter(_ != 0).toSet.size
  }

  def maximumGroups_failed(grades: Array[Int]): Int = {
    val bigToSmall = grades.sorted.reverse
    var count = 0
    var capacity = 1
    var i = 0
    var from = 0
    var sum = 0
    var preSum = 0
    while (i < bigToSmall.length) {
      from = i
      preSum = sum
      while (i < bigToSmall.length && i - from < capacity) {
        sum += bigToSmall(i)
        i += 1
      }
      while (i < bigToSmall.length && sum <= preSum) {
        sum += bigToSmall(i)
        capacity += 1
        i += 1
      }
      if (sum <= preSum) return count
      count += 1
      i += 1
      capacity += 1
    }
    count
  }

  /**
   * 6133. 分组的最大数量 | 难度：中等
   * 68 / 68 个通过测试用例
   * 状态：通过
   * 执行用时: 700 ms
   * 内存消耗: 75 MB
   *
   * @param grades
   * @return
   */
  def maximumGroups(grades: Array[Int]): Int = {
    var count = 0
    var total = 0
    while (total + count + 1 <= grades.length) {
      count += 1
      total += count
    }
    count
  }

  /**
   * 6134. 找到离给定两个节点最近的节点 | 难度：中等
   * 72 / 72 个通过测试用例
   * 状态：通过
   * 执行用时: 992 ms
   * 内存消耗: 75.3 MB
   *
   * @param edges
   * @param node1
   * @param node2
   * @return
   */
  def closestMeetingNode(edges: Array[Int], node1: Int, node2: Int): Int = {
    if (node1 == node2) return node1
    val set1 = new scala.collection.mutable.HashSet[Int]
    val set2 = new scala.collection.mutable.HashSet[Int]
    var ptr1 = node1
    var ptr2 = node2
    var loop1 = false
    var loop2 = false
    var find = false
    while (!find && ((!loop1 && ptr1 != -1) || (!loop2 && ptr2 != -1)) && !set2.contains(ptr1) && !set1.contains(ptr2)) {
      if (ptr1 == ptr2) return ptr1
      if (!loop1 && ptr1 >= 0) {
        if (set1.contains(edges(ptr1))) loop1 = true
        else {
          set1.add(ptr1)
          ptr1 = edges(ptr1)
        }
      }
      if (set1.contains(ptr2)) find = true
      if (!loop2 && ptr2 >= 0) {
        if (set2.contains(edges(ptr2))) loop2 = true
        else {
          set2.add(ptr2)
          ptr2 = edges(ptr2)
        }
      }
    }
    if (ptr1 >= 0) set1.add(ptr1)
    if (ptr2 >= 0) set2.add(ptr2)
    val intersect = set1 intersect set2
    if (intersect.nonEmpty) intersect.min
    else -1
  }

  /**
   * 6135. 图中的最长环 | 难度：困难
   * 72 / 72 个通过测试用例
   * 状态：通过
   * 执行用时: 932 ms
   * 内存消耗: 81.5 MB
   *
   * @param edges
   * @return
   */
  def longestCycle(edges: Array[Int]): Int = {
    var result = -1
    val visit = new Array[Int](edges.length)
    for (i <- edges.indices if visit(i) == 0) {
      var ptr = i
      var continue = true
      while (continue && ptr >= 0) {
        if (visit(ptr) == 0) {
          visit(ptr) = 1
          ptr = edges(ptr)
        } else {
          continue = false
        }
      }
      if (ptr == -1 || visit(ptr) == 3) {
        ptr = i
        while (ptr != -1 && visit(ptr) != 3) {
          visit(ptr) = 3
          ptr = edges(ptr)
        }
      } else if (visit(ptr) == 1) {
        var count = 0
        while (visit(ptr) != 2) {
          count += 1
          visit(ptr) = 2
          ptr = edges(ptr)
        }
        result = math.max(result, count)
      }
    }
    result
  }

  def main(args: Array[String]): Unit = {
    // test3()
    println(longestCycle(Array(-1, 4, -1, 2, 0, 4))) // -1
  }

  private def test3() = {
    println(closestMeetingNode(Array(1, 2, -1), 0, 2))
    println(closestMeetingNode(Array(2, 0, 0), 2, 0)) // 0
    println(closestMeetingNode(Array(5, 4, 5, 4, 3, 6, -1), 0, 1)) // -1
    println(closestMeetingNode(Array(2, 0, -1, -1, -1, 1, 1, 4, 1), 6, 8)) // 1
  }
}
