package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/24 10:51
 * @note
 * 915. 分割数组 | 难度：中等
 */
object Solution915 {
  def main(args: Array[String]): Unit = {
    println(Array(0).scanLeft(-1)((preMax, now) => preMax max now).mkString("Array(", ", ", ")")) // Array(-1, 0)
    partitionDisjoint(Array(5, 0, 3, 8, 6))
  }

  /**
   * 时间 1092 ms 击败 100%
   * 内存 74.1 MB 击败 100%
   *
   * @param nums
   * @return
   */
  def partitionDisjoint(nums: Array[Int]): Int = {
    val maxes = nums.scanLeft(-1)((preMax, now) => preMax max now)
    var min = Int.MaxValue
    var res = nums.length
    for (i <- nums.length - 1 to 1 by -1) {
      min = min min nums(i)
      if (maxes(i) <= min) {
        res = i
      }
    }
    res
  }
}
