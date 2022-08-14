package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/6/30 18:37
 * @note
 * 面试题 16.21. 交换和 | 难度：中等 | 标签：数组、哈希表、二分查找、排序
 * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 *
 * 示例:
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 *
 * 提示：
 * 1 <= array1.length, array2.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-swap-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_21 {
  def main(args: Array[String]): Unit = {
    println(findSwapValues(Array(1, 2, 3), Array(4, 5, 6)).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：772 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：34 / 34
   *
   * @param array1
   * @param array2
   * @return
   */
  def findSwapValues(array1: Array[Int], array2: Array[Int]): Array[Int] = {
    val sum1 = array1.sum
    val sum2 = array2.sum
    if (math.abs(sum1 - sum2) % 2 == 1) return Array.empty
    val sorted1 = array1.sorted
    val sorted2 = array2.sorted
    val diff = (sum1 - sum2) / 2
    var p1 = 0
    var p2 = 0
    while (p1 < sorted1.length && p2 < sorted2.length) {
      if (sorted1(p1) - sorted2(p2) > diff) p2 += 1
      else if (sorted1(p1) - sorted2(p2) < diff) p1 += 1
      else return Array(sorted1(p1), sorted2(p2))
    }
    Array.empty
  }
}
