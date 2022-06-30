package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 11:54
 * @note
 * 面试题 16.06. 最小差 | 难度：中等 | 标签：数组、双指针、二分查找、排序
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 *
 * 示例：
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 *
 * 提示：
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-difference-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_06 {
  def main(args: Array[String]): Unit = {
    println(smallestDifference(Array(-2147483648, 1), Array(2147483647, 0)))
  }

  /**
   * 执行用时：780 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：60 / 60
   *
   * @param a
   * @param b
   * @return
   */
  def smallestDifference(a: Array[Int], b: Array[Int]): Int = {
    val sortedA = a.sorted
    val sortedB = b.sorted
    var ptrA = 0
    var ptrB = 0
    var min = Int.MaxValue
    var abs: Long = 0L
    while (ptrA < a.length && ptrB < b.length) {
      abs = math.abs(sortedB(ptrB).toLong - sortedA(ptrA))
      while (ptrB < b.length - 1 && math.abs(sortedB(ptrB + 1).toLong - sortedA(ptrA)) < abs) {
        ptrB += 1
        abs = math.abs(sortedB(ptrB).toLong - sortedA(ptrA))
      }
      min = math.min(min, if (abs > Int.MaxValue) Int.MaxValue else abs.toInt)
      ptrA += 1
    }
    while (ptrA < a.length) {
      abs = math.abs(sortedB(ptrB - 1).toLong - sortedA(ptrA))
      min = math.min(min, if (abs > Int.MaxValue) Int.MaxValue else abs.toInt)
      ptrA += 1
    }
    min
  }
}
