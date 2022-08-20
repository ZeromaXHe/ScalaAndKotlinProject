package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/8/20 21:40
 * @note
 * 第 85 场双周赛
 */
object BiweeklyContest85 {
  /**
   * 6156. 得到 K 个黑块的最少涂色次数 | 难度：简单
   * 118 / 118 个通过测试用例
   * 状态：通过
   * 执行用时: 432 ms
   * 内存消耗: 52.2 MB
   *
   * @param blocks
   * @param k
   * @return
   */
  def minimumRecolors(blocks: String, k: Int): Int = {
    var countW = 0
    for (i <- 0 until k) {
      if (blocks(i) == 'W') countW += 1
    }
    var min = countW
    for (i <- 0 until blocks.length - k) {
      if (blocks(i) == 'W') countW -= 1
      if (blocks(i + k) == 'W') countW += 1
      min = math.min(countW, min)
    }
    min
  }

  def secondsToRemoveOccurrences_wrong(s: String): Int = {
    var i = 0
    while (i < s.length && s(i) == '1') {
      i += 1
    }
    if (i >= s.length - 1) return 0
    var count0 = 0
    var count1 = 0
    var temp0 = 1
    for (j <- i + 1 until s.length) {
      if (s(j) == '1') {
        count1 += 1
        count0 += temp0
        temp0 = 0
      } else {
        temp0 += 1
      }
    }
    if (count1 == count0) count0 + 1 else math.max(count1, count0)
  }

  /**
   * 6157. 二进制字符串重新安排顺序需要的时间 | 难度：中等
   * 103 / 103 个通过测试用例
   * 状态：通过
   * 执行用时: 476 ms
   * 内存消耗: 52.5 MB
   *
   * @param s
   * @return
   */
  def secondsToRemoveOccurrences(s: String): Int = {
    var count = 0
    val chars = s.toCharArray
    var i = 1
    while (count < 1000) {
      var change = false
      while (i < s.length) {
        if (chars(i) == '1' && chars(i - 1) == '0') {
          chars(i) = '0'
          chars(i - 1) = '1'
          i += 2
          change = true
        } else i += 1
      }
      if (!change) return count
      count += 1
      i = 1
    }
    count
  }

  /**
   * 6158. 字母移位 II | 难度：中等
   * 39 / 39 个通过测试用例
   * 状态：通过
   * 执行用时: 952 ms
   * 内存消耗: 81.9 MB
   *
   * @param s
   * @param shifts
   * @return
   */
  def shiftingLetters(s: String, shifts: Array[Array[Int]]): String = {
    val diff = new Array[Int](s.length + 1)
    shifts.foreach(shift => {
      val mov = if (shift(2) == 0) 1 else -1
      diff(shift(0)) -= mov
      diff(shift(1) + 1) += mov
    })
    val builder = StringBuilder.newBuilder
    var move = 0
    for (i <- s.indices) {
      move += diff(i)
      val div = (s(i) - 'a' + move) % 26
      builder.append(((if (div < 0) 26 + div else div) + 'a').toChar)
    }
    builder.toString()
  }

  /**
   * 6159. 删除操作后的最大子段和 | 难度：困难
   * 44 / 44 个通过测试用例
   * 状态：通过
   * 执行用时: 1656 ms
   * 内存消耗: 81.1 MB
   *
   * @param nums
   * @param removeQueries
   * @return
   */
  def maximumSegmentSum(nums: Array[Int], removeQueries: Array[Int]): Array[Long] = {
    // 大顶堆
    val heap = new scala.collection.mutable.PriorityQueue[(Long, Int, Int)]
    val preSum = new Array[Long](nums.length + 1)
    var sum = 0L
    for (i <- nums.indices) {
      sum += nums(i)
      preSum(i + 1) = sum
    }
    heap.enqueue((nums.sum, 0, nums.length))
    val treeset = new java.util.TreeSet[Integer]
    (for (i <- removeQueries.indices) yield {
      treeset.add(removeQueries(i))
      var ceilFrom = treeset.ceiling(heap.head._2)
      while (ceilFrom != null && ceilFrom < heap.head._3) {
        val deq = heap.dequeue()
        if (ceilFrom != deq._2) heap.enqueue((preSum(ceilFrom) - preSum(deq._2), deq._2, ceilFrom))
        if (ceilFrom != deq._3 - 1) heap.enqueue((preSum(deq._3) - preSum(ceilFrom + 1), ceilFrom + 1, deq._3))
        treeset.remove(ceilFrom)
        ceilFrom = if (heap.isEmpty) null else treeset.ceiling(heap.head._2)
      }
      if (heap.isEmpty) 0 else heap.head._1
    }).toArray
  }

  def main(args: Array[String]): Unit = {
    println(secondsToRemoveOccurrences("001011")) // 4
    println(secondsToRemoveOccurrences("010101")) // 3
    // 001011
    // 010101
    // 101010
    // 110100
    // 111000
    println(maximumSegmentSum(Array(1, 2, 5, 6, 1), Array(0, 3, 2, 4, 1)).mkString("Array(", ", ", ")"))
  }
}
