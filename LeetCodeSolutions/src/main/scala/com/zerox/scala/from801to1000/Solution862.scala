package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/26 9:49
 * @note
 * 862. 和至少为 K 的最短子数组 | 难度：困难
 */
object Solution862 {
  /**
   * 时间 1196 ms 击败 100%
   * 内存 80.5 MB 击败 100%
   *
   * @param nums
   * @param k
   * @return
   */
  def shortestSubarray(nums: Array[Int], k: Int): Int = {
    var res = Int.MaxValue
    val q = new scala.collection.mutable.ArrayDeque[(Long, Integer)]
    q.addOne((0L, -1))
    var curS = 0L
    for (i <- nums.indices) {
      curS += nums(i)
      while (q.nonEmpty && curS - q.head._1 >= k) res = res min (i - q.removeHead()._2)
      while (q.nonEmpty && q.last._1 >= curS) q.removeLast()
      q.addOne((curS, i))
    }
    if (res == Int.MaxValue) -1 else res
  }
}
