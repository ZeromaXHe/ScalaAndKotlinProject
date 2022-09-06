package com.zerox.scala.from1601to1800

import com.zerox.scala.aider.BinarySearcher
import com.zerox.scala.aider.DynamicProgrammingUtils.dpTwoDimension

/**
 * @author zhuxi
 * @since 2022/9/6 11:03
 * @note
 * 1751. 最多可以参加的会议数目 II | 难度：困难 | 标签：数组、二分查找、动态规划
 * 给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 startDayi 天开始，第 endDayi 天结束，如果你参加这个会议，你能得到价值 valuei 。同时给你一个整数 k 表示你能参加的最多会议数目。
 *
 * 你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 完整 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。
 *
 * 请你返回能得到的会议价值 最大和 。
 *
 * 示例 1：
 * 输入：events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
 * 输出：7
 * 解释：选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。
 *
 * 示例 2：
 * 输入：events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
 * 输出：10
 * 解释：参加会议 2 ，得到价值和为 10 。
 * 你没法再参加别的会议了，因为跟会议 2 有重叠。你 不 需要参加满 k 个会议。
 *
 * 示例 3：
 * 输入：events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
 * 输出：9
 * 解释：尽管会议互不重叠，你只能参加 3 个会议，所以选择价值最大的 3 个会议。
 *
 * 提示：
 * 1 <= k <= events.length
 * 1 <= k * events.length <= 106
 * 1 <= startDayi <= endDayi <= 109
 * 1 <= valuei <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1751 {
  def main(args: Array[String]): Unit = {
    println(maxValue(Array(Array(1, 2, 4), Array(3, 4, 3), Array(2, 3, 10)), 2)) // 10
  }

  /**
   * 执行用时：1280 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：129.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：67 / 67
   *
   * @param events
   * @param k
   * @return
   */
  def maxValue(events: Array[Array[Int]], k: Int): Int = {
    val n = events.length
    val sort = events.sortBy(_ (1))
    val dp = dpTwoDimension(n, k, List.empty,
      i => {
        val bs = new BinarySearcher[Int](p => sort(p.toInt - 1)(1))
        val result = bs.binarySearch(sort(i - 1)(0) - 1, 1, i, findGreatest = true, less = true)
        val last = if (result.result.isEmpty) 0 else result.index.toInt
        Array(last, sort(i - 1)(2))
      },
      (dp, i, j, params) => dp(i - 1)(j) max (dp(params(0))(j - 1) + params(1))
    )
    dp(n)(k)
  }
}
