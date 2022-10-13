package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/10/13 9:54
 * @note
 * 769. 最多能完成排序的块 | 难度：中等
 */
object Solution769 {
  /**
   * 时间 484 ms 击败 100%
   * 内存 52.8 MB 击败 100%
   *
   * @param arr
   * @return
   */
  def maxChunksToSorted(arr: Array[Int]): Int = {
    val map = arr.sorted.zipWithIndex.toMap
    var max = -1
    var res = 0
    for (i <- arr.indices) {
      max = max max map(arr(i))
      if (max == i) res += 1
    }
    res
  }
}
