package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/7/8 9:49
 * @note
 * 1217. 玩筹码 | 难度：简单 | 标签：贪心、数组、数学
 * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
 *
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
 *
 * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
 * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 *
 * 示例 1：
 * 输入：position = [1,2,3]
 * 输出：1
 * 解释：第一步:将位置3的筹码移动到位置1，成本为0。
 * 第二步:将位置2的筹码移动到位置1，成本= 1。
 * 总成本是1。
 *
 * 示例 2：
 * 输入：position = [2,2,2,3,3]
 * 输出：2
 * 解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
 *
 * 示例 3:
 * 输入：position = [1,1000000000]
 * 输出：1
 *
 * 提示：
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1217 {
  /**
   * 执行用时：476 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：51 / 51
   *
   * @param position
   * @return
   */
  def minCostToMoveChips(position: Array[Int]): Int = {
    val tuple = position.partition(_ % 2 == 0)
    math.min(tuple._1.length, tuple._2.length)
  }
}
