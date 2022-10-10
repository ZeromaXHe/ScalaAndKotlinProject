package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/10/10 13:57
 * @note
 * 801. 使序列递增的最小交换次数 | 难度：困难
 */
object Solution801 {
  def main(args: Array[String]): Unit = {
    println(minSwap(Array(1, 3, 5, 4), Array(1, 2, 3, 7))) // 1
    println(minSwap(Array(0, 3, 5, 8, 9), Array(2, 1, 4, 6, 9))) // 1
    println(minSwap(Array(0, 4, 4, 5, 9), Array(0, 1, 6, 8, 10))) // 1
  }

  /**
   * 执行用时：908 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：79.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：117 / 117
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def minSwap(nums1: Array[Int], nums2: Array[Int]): Int = {
    val n = nums1.length
    var notSwap = 0
    var swap = 1
    for (i <- 1 until n) {
      val tn = notSwap
      val ts = swap
      notSwap = n
      swap = n
      if (nums1(i) > nums1(i - 1) && nums2(i) > nums2(i - 1)) {
        notSwap = notSwap min tn
        swap = swap min (ts + 1)
      }
      if (nums1(i) > nums2(i - 1) && nums2(i) > nums1(i - 1)) {
        notSwap = notSwap min ts
        swap = swap min (tn + 1)
      }
    }
    swap min notSwap
  }
}
