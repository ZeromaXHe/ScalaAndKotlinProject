package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/8 11:35
 * @note
 * 870. 优势洗牌 | 难度：中等
 */
object Solution870 {
  /**
   * 执行用时：1088 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：84.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：67 / 67
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def advantageCount(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    val sorted1 = nums1.sorted
    val sorted2 = nums2.zipWithIndex.sorted
    val res = new Array[Int](sorted1.length)
    var p1 = 0
    var p2 = res.length - 1
    for (i <- sorted1.indices) {
      if (sorted1(i) <= sorted2(p1)._1) {
        res(sorted2(p2)._2) = sorted1(i)
        p2 -= 1
      } else {
        res(sorted2(p1)._2) = sorted1(i)
        p1 += 1
      }
    }
    res
  }
}
