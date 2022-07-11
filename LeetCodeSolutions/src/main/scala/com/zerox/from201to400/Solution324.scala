package com.zerox.from201to400

/**
 * @author zhuxi
 * @since 2022/6/28 9:53
 * @note
 * 324. 摆动排序 II | 难度：中等 | 标签：数组、分治、快速选择、排序
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 *
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 *
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 *
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/wiggle-sort-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution324 {
  def main(args: Array[String]): Unit = {
    for (i <- 0 until 6) {
      println(map(6, i))
    }
    println("-----")
    for (i <- 0 until 5) {
      println(map(5, i))
    }
    val nums = Array(4, 5, 5, 6)
    wiggleSort(nums)
    println(nums.mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：616 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * @param nums
   */
  def wiggleSort(nums: Array[Int]): Unit = {
    val n = nums.length
    val count = new Array[Int](5001)
    for (elem <- nums) {
      count(elem) += 1
    }
    var ptr = 5000
    var i = 1
    while (i < n) {
      while (count(ptr) == 0) {
        ptr -= 1
      }
      nums(i) = ptr
      count(ptr) -= 1
      i += 2
    }
    i = 0
    while (i < n) {
      while (count(ptr) == 0) {
        ptr -= 1
      }
      nums(i) = ptr
      count(ptr) -= 1
      i += 2
    }
  }

  /**
   * 执行用时：612 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * @param nums
   */
  def wiggleSort_bucketSortAndRangeFor(nums: Array[Int]): Unit = {
    val n = nums.length
    val count = new Array[Int](5001)
    nums.foreach(count(_) += 1)
    var ptr = 5000

    def next(i: Int) = {
      while (count(ptr) == 0) {
        ptr -= 1
      }
      nums(i) = ptr
      count(ptr) -= 1
    }

    for (i <- Range(1, n, 2)) next(i)
    for (i <- Range(0, n, 2)) next(i)
  }

  /**
   * 执行用时：684 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * @param nums
   */
  def wiggleSort_bucketSortAndRangeForeach(nums: Array[Int]): Unit = {
    val n = nums.length
    val count = new Array[Int](5001)
    nums.foreach(count(_) += 1)
    var ptr = 5000;
    val next: Int => Unit = i => {
      while (count(ptr) == 0) {
        ptr -= 1
      }
      nums(i) = ptr
      count(ptr) -= 1
    }
    Range(1, n, 2).foreach(next)
    Range(0, n, 2).foreach(next)
  }

  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * @param nums
   */
  def wiggleSort_sort(nums: Array[Int]): Unit = {
    nums.sorted.zipWithIndex.foreach(t => nums(map(nums.length, t._2)) = t._1)
  }

  private def map(len: Int, index: Int): Int = {
    if (index > (len - 1) / 2) (len - 1 - index) * 2 + 1
    else ((len - 1) / 2 - index) * 2
  }

  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * 偷懒了，没有去研究 O(n) 复杂度的方法
   *
   * @param nums
   */
  def wiggleSort_for(nums: Array[Int]): Unit = {
    val sorted = nums.sorted
    for (i <- nums.indices) {
      if (i % 2 == 0) {
        nums(i) = sorted((nums.length - 1) / 2 - i / 2)
      } else {
        nums(i) = sorted(nums.length - 1 - i / 2);
      }
    }
  }
}
