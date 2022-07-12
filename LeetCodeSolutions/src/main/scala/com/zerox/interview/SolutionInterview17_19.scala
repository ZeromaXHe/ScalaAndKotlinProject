package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/12 10:44
 * @note
 * 面试题 17.19. 消失的两个数字 | 难度：困难 | 标签：位运算、数组、哈希表
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 *
 * 以任意顺序返回这两个数字均可。
 *
 * 示例 1:
 * 输入: [1]
 * 输出: [2,3]
 *
 * 示例 2:
 * 输入: [2,3]
 * 输出: [1,4]
 *
 * 提示：
 * nums.length <= 30000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/missing-two-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_19 {
  def main(args: Array[String]): Unit = {
    println(missingTwo_hash(Array(1)).mkString("Array(", ", ", ")"))
    println(missingTwo_hash(Array(2)).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：696 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：44 / 44
   *
   * @param nums
   * @return
   */
  def missingTwo(nums: Array[Int]): Array[Int] = {
    var xor = 0
    var xorTotal = 0
    for (i <- nums.indices) {
      xor ^= nums(i)
      xorTotal ^= (i + 1)
    }
    xorTotal = (xorTotal ^ (nums.length + 1)) ^ (nums.length + 2)
    val xXorY = xor ^ xorTotal
    var multi = 1
    var find = false
    while (!find && multi < xXorY) {
      if ((xXorY & multi) > 0) find = true
      else multi <<= 1
    }
    var xor1 = 0
    var xor2 = 0
    var xorTotal1 = 0
    var xorTotal2 = 0
    for (i <- nums.indices) {
      if ((nums(i) & multi) == 0) xor1 ^= nums(i) else xor2 ^= nums(i)
      if (((i + 1) & multi) == 0) xorTotal1 ^= (i + 1) else xorTotal2 ^= (i + 1)
    }
    if (((nums.length + 1) & multi) == 0) xorTotal1 ^= (nums.length + 1) else xorTotal2 ^= (nums.length + 1)
    if (((nums.length + 2) & multi) == 0) xorTotal1 ^= (nums.length + 2) else xorTotal2 ^= (nums.length + 2)
    Array(xorTotal1 ^ xor1, xorTotal2 ^ xor2)
  }

  /**
   * 执行用时：636 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：44 / 44
   *
   * @param nums
   * @return
   */
  def missingTwo_hash(nums: Array[Int]): Array[Int] = {
    var plus1 = true
    var plus2 = true
    for (i <- nums.indices) {
      if (nums(i).abs == nums.length + 1) plus1 = false
      else if (nums(i).abs == nums.length + 2) plus2 = false
      else {
        val index = nums(i).abs - 1
        nums(index) = -nums(index).abs
      }
    }
    if (plus1 && plus2) Array(nums.length + 1, nums.length + 2)
    else if (plus1 || plus2) {
      for (i <- nums.indices if nums(i) > 0) return Array(nums.length + (if (plus1) 1 else 2), i + 1)
      throw new RuntimeException("will not execute")
    } else {
      var first = -1
      for (i <- nums.indices if nums(i) > 0) {
        if (first == -1) first = i + 1
        else return Array(first, i + 1)
      }
      throw new RuntimeException("will not execute")
    }
  }
}
