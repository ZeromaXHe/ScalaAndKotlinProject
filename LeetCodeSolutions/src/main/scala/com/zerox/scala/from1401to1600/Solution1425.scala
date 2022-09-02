package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/9/2 11:58
 * @note
 * 1425. 带限制的子序列和 | 难度：困难 | 标签：队列、数组、动态规划、滑动窗口、单调队列、堆（优先队列）
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和 nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
 *
 * 数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
 *
 * 示例 1：
 * 输入：nums = [10,2,-10,5,20], k = 2
 * 输出：37
 * 解释：子序列为 [10, 2, 5, 20] 。
 *
 * 示例 2：
 * 输入：nums = [-1,-2,-3], k = 1
 * 输出：-1
 * 解释：子序列必须是非空的，所以我们选择最大的数字。
 *
 * 示例 3：
 * 输入：nums = [10,-2,-10,-5,20], k = 2
 * 输出：23
 * 解释：子序列为 [10, -2, -5, 20] 。
 *
 * 提示：
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/constrained-subsequence-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1425 {
  /**
   * 执行用时：1468 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：77.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：36 / 36
   *
   * @param nums
   * @param k
   * @return
   */
  def constrainedSubsetSum(nums: Array[Int], k: Int): Int = {
    val pq = new scala.collection.mutable.PriorityQueue[(Int, Int)]
    pq.enqueue((nums(0), 0))
    var res = nums(0)
    for (i <- 1 until nums.length) {
      while (pq.head._2 < i - k) pq.dequeue()
      val dp = (pq.head._1 max 0) + nums(i)
      pq.enqueue((dp, i))
      if (dp > res) res = dp
    }
    res
  }
}
