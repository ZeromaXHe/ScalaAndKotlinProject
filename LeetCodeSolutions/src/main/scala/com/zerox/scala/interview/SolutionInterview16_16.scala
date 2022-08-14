package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/6/30 16:16
 * @note
 * 面试题 16.16. 部分排序 | 难度：中等 | 标签：栈、贪心、数组、双指针、排序、单调栈
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 *
 * 提示：
 * 0 <= len(array) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sub-sort-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_16 {
  def main(args: Array[String]): Unit = {
    println(subSort(Array(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：808 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：102.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：38 / 38
   *
   * @param array
   * @return
   */
  def subSort(array: Array[Int]): Array[Int] = {
    if (array.length <= 1) return Array(-1, -1)
    var min = Int.MaxValue
    var max = Int.MinValue
    val maxes = for (i <- array.indices) yield {
      max = math.max(max, array(i))
      max
    }
    val mines = for (i <- array.indices.reverse) yield {
      min = math.min(min, array(i))
      min
    }
    var i = 0
    while (i >= 0 && i < array.length) {
      if (mines(array.length - 1 - i) != array(i)) i = -i - 1
      else i += 1
    }
    var j = array.length - 1
    while (j >= 0) {
      if (maxes(j) != array(j)) j = -j - 2
      else j -= 1
    }
    i = if (i == array.length) i else -i - 1
    j = if (j == -1) 0 else -j - 2
    if (i > j) Array(-1, -1)
    else Array(i, j)
  }
}
