package com.zerox.scala.from1001to1200

/**
 * @author zhuxi
 * @since 2022/9/21 11:46
 * @note
 * 1035. 不相交的线 | 难度：中等 | 标签：数组、动态规划
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 *
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 *
 *  nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 *
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 *
 * 示例 1：
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 *
 * 示例 2：
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 *
 * 示例 3：
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/uncrossed-lines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1035 {
  /**
   * 执行用时：504 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：74 / 74
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def maxUncrossedLines(nums1: Array[Int], nums2: Array[Int]): Int = {
    val dp = Array.ofDim[Int](nums1.length + 1, nums2.length + 1)
    for (i <- nums1.indices; j <- nums2.indices) {
      dp(i + 1)(j + 1) = dp(i + 1)(j) max dp(i)(j + 1) max (dp(i)(j) + (if (nums1(i) == nums2(j)) 1 else 0))
    }
    dp(nums1.length)(nums2.length)
  }
}
