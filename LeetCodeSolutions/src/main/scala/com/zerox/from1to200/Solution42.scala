package com.zerox.from1to200

import com.zerox.interview.SolutionInterview17_21

/**
 * @author zhuxi
 * @since 2022/7/12 15:56
 * @note
 * 42. 接雨水 | 难度：困难 | 标签：栈、数组、双指针、动态规划、单调栈
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution42 {
  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 40.00% 的用户
   * 内存消耗：54.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：322 / 322
   *
   * @param height
   * @return
   */
  def trap(height: Array[Int]): Int = {
    SolutionInterview17_21.trap(height)
  }

  /**
   * 执行用时：572 ms, 在所有 Scala 提交中击败了 60.00% 的用户
   * 内存消耗：56 MB, 在所有 Scala 提交中击败了 60.00% 的用户
   * 通过测试用例：322 / 322
   *
   * Scala 里面动规貌似就是比双指针快，离谱……
   *
   * @param height
   * @return
   */
  def trap_dp(height: Array[Int]): Int = {
    SolutionInterview17_21.trap_dp(height)
  }
}
