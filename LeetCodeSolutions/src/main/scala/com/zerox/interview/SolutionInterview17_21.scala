package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/12 14:16
 * @note
 * 面试题 17.21. 直方图的水量 | 难度：困难 | 标签：栈、数组、双指针、动态规划、单调栈
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_21 {
  /**
   * 执行用时：496 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：315 / 315
   *
   * 和 42 题一样。执行结果很神奇，动规跑三遍居然最快，就离谱……
   *
   * @param height
   * @return
   */
  def trap_dp(height: Array[Int]): Int = {
    val maxLeft = new Array[Int](height.length + 1)
    maxLeft(0) = 0
    val maxRight = new Array[Int](height.length + 1)
    maxRight(height.length) = 0
    for (i <- height.indices) {
      maxLeft(i + 1) = math.max(maxLeft(i), height(i))
    }
    for (i <- height.indices.reverse) {
      maxRight(i) = math.max(maxRight(i + 1), height(i))
    }
    var sum = 0
    for (i <- height.indices) {
      sum += math.min(maxLeft(i + 1), maxRight(i)) - height(i)
    }
    sum
  }

  /**
   * 执行用时：540 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：315 / 315
   *
   * @param height
   * @return
   */
  def trap_monotonicStack(height: Array[Int]): Int = {
    var sum = 0
    val stack = new scala.collection.mutable.Stack[Int]
    for (i <- height.indices) {
      var continue = true
      while (continue && stack.nonEmpty && height(i) > height(stack.head)) {
        val top = stack.pop()
        if (stack.isEmpty) continue = false
        else {
          val width = i - stack.head - 1
          val deep = math.min(height(stack.head), height(i)) - height(top)
          sum += width * deep
        }
      }
      stack.push(i)
    }
    sum
  }

  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：315 / 315
   *
   * @param height
   * @return
   */
  def trap(height: Array[Int]): Int = {
    // 定理一：在某个位置 i 处，它能存的水，取决于它左右两边的最大值中较小的一个。
    // 定理二：当我们从左往右处理到 l 下标时，左边的最大值 maxL 对它而言是可信的，但 maxR 对它而言是不可信的。
    // （由于中间状况未知，对于 l 下标而言，maxR 未必就是它右边最大的值）
    // 定理三：当我们从右往左处理到 r 下标时，右边的最大值 maxR 对它而言是可信的，但 maxL 对它而言是不可信的。
    var l = 0
    var r = height.length - 1
    var sum = 0
    var maxL = 0
    var maxR = 0
    while (l <= r) {
      if (maxL < maxR) {
        sum += math.max(0, maxL - height(l))
        maxL = math.max(maxL, height(l))
        l += 1
      } else {
        sum += math.max(0, maxR - height(r))
        maxR = math.max(maxR, height(r))
        r -= 1
      }
    }
    sum
  }
}
