package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/10/29 15:53
 * @note
 * 163. 缺失的区间 | 难度：简单 | 标签：数组
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 */
object Solution163 {
  /**
   * 时间 436 ms 击败 100%
   * 内存 52.4 MB 击败 100%
   *
   * @param nums
   * @param lower
   * @param upper
   * @return
   */
  def findMissingRanges(nums: Array[Int], lower: Int, upper: Int): List[String] = {
    val res = new scala.collection.mutable.ListBuffer[String]
    var ptr = lower
    var i = 0
    while (i < nums.length && nums(i) < lower) i += 1
    while (i < nums.length && nums(i) <= upper) {
      if (nums(i) > ptr) {
        if (nums(i) == ptr + 1) res.addOne(ptr.toString)
        else res.addOne(ptr + "->" + (nums(i) - 1))
        ptr = nums(i) + 1
      } else ptr += 1
      i += 1
    }
    if (ptr < upper) res.addOne(ptr + "->" + upper)
    else if (ptr == upper) res.addOne(upper.toString)
    res.toList
  }
}
