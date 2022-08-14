package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/9 22:27
 * @note
 * 第 82 场双周赛
 */
object BiweeklyContest82 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 6116. 计算布尔二叉树的值 | 难度：简单
   * 75 / 75 个通过测试用例
   * 状态：通过
   * 执行用时: 516 ms
   * 内存消耗: 55.4 MB
   *
   * @param root
   * @return
   */
  def evaluateTree(root: TreeNode): Boolean = {
    if (root.left == null && root.right == null) {
      return root.value == 1
    }
    root.value match {
      case 2 => evaluateTree(root.left) || evaluateTree(root.right)
      case 3 => evaluateTree(root.left) && evaluateTree(root.right)
      case _ => throw new RuntimeException("error")
    }
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  /**
   * 6117. 坐上公交的最晚时间 | 难度：中等
   * 50 / 50 个通过测试用例
   * 状态：通过
   * 执行用时: 840 ms
   * 内存消耗: 81 MB
   *
   * @param buses
   * @param passengers
   * @param capacity
   * @return
   */
  def latestTimeCatchTheBus(buses: Array[Int], passengers: Array[Int], capacity: Int): Int = {
    var passIndex = 0
    val pass = passengers.sorted
    val bus = buses.sorted
    var lastEmpty = pass(0) - 1
    var count = 0
    var busIndex = 0
    while (busIndex < buses.length) {
      count = 0
      while (count < capacity && pass(passIndex) <= bus(busIndex)) {
        if (passIndex > 0 && pass(passIndex) - 1 != pass(passIndex - 1)) lastEmpty = pass(passIndex) - 1
        passIndex += 1
        if (passIndex == pass.length) {
          if (busIndex < bus.length - 1) return bus(bus.length - 1)
          if (count == capacity - 1) return lastEmpty
          if (pass(passIndex - 1) != bus(bus.length - 1)) return bus(bus.length - 1)
          else return lastEmpty
        }
        count += 1
      }
      busIndex += 1
    }
    if (count < capacity && (passIndex == 0 || pass(passIndex - 1) != bus(bus.length - 1))) bus(bus.length - 1)
    else lastEmpty
  }

  /**
   * 6118. 最小差值平方和 | 难度：中等
   * 37 / 37 个通过测试用例
   * 状态：通过
   * 执行用时: 1048 ms
   * 内存消耗: 82.3 MB
   *
   * @param nums1
   * @param nums2
   * @param k1
   * @param k2
   * @return
   */
  def minSumSquareDiff(nums1: Array[Int], nums2: Array[Int], k1: Int, k2: Int): Long = {
    val sorted = (nums1 zip nums2).map(t => (t._1 - t._2).abs).sorted
    var sum = 0
    var i = sorted.length - 1
    while (i >= 0 && sum < k1 + k2) {
      i -= 1
      sum += (if (i >= 0) sorted(i + 1) - sorted(i) else sorted(i + 1)) * (sorted.length - 1 - i)
    }
    if (sum <= k1 + k2 && i == -1) {
      return 0
    }
    var total = 0L
    for (index <- 0 to i) {
      total += sorted(index).toLong * sorted(index)
    }
    if (i == sorted.length - 1) {
      return total
    }
    val base = if (i == -1) 0 else sorted(i)
    val add = (sum - k1 - k2) / (sorted.length - 1 - i)
    val more1 = (sum - k1 - k2) % (sorted.length - 1 - i)
    total += (base + add + 1).toLong * (base + add + 1) * more1
    total += (base + add).toLong * (base + add) * (sorted.length - 1 - i - more1)
    total
  }

  /**
   * 6119. 元素值大于变化阈值的子数组 | 难度：困难
   * 未完成
   *
   * @param nums
   * @param threshold
   * @return
   */
  def validSubarraySize(nums: Array[Int], threshold: Int): Int = {
    var i = 1
    while (i <= nums.length) {
      var j = 0
      var count = 0
      var continue = true
      while (continue && j < nums.length) {
        if (nums(j) > threshold / i) {
          count += 1
          if (count >= i) return i
        } else {
          count = 0
          if (count > nums.length - i) {
            continue = false
          }
        }
        j += 1
      }
      i += 1
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    println(validSubarraySize(Array(1, 3, 4, 3, 1), 6) == 3)
    println(validSubarraySize(Array(1000000000), 1) == 1)
  }

  private def test3 = {
    println(minSumSquareDiff(Array(1, 2, 3, 4), Array(2, 10, 20, 19), 0, 0) == 579)
    println(minSumSquareDiff(Array(1, 4, 10, 12), Array(5, 8, 6, 9), 1, 1) == 43)
    println(minSumSquareDiff(Array(1, 4, 10, 12), Array(5, 8, 6, 9), 10, 5))
    println(minSumSquareDiff(Array(1), Array(5), 10, 0) == 0)
    println(minSumSquareDiff(Array(1), Array(5), 10, 1) == 0)
    println(minSumSquareDiff(Array(1), Array(5), 1, 1) == 4)
  }

  private def test2 = {
    println(latestTimeCatchTheBus(Array(20, 30, 10), Array(19, 13, 26, 4, 25, 11, 21), 2) == 20)
    println(latestTimeCatchTheBus(Array(20, 10), Array(2, 17, 18, 19), 2) == 16)
    println(latestTimeCatchTheBus(Array(3), Array(2, 4), 2) == 3)
    println(latestTimeCatchTheBus(Array(3), Array(2, 3), 2) == 1)
    println(latestTimeCatchTheBus(Array(3), Array(4), 1) == 3)
    println(latestTimeCatchTheBus(Array(6, 8, 18, 17), Array(6, 8, 17), 1) /*== 18*/)
  }
}
