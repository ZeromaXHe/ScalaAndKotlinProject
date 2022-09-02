package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/9/2 15:39
 * @note
 * 1335. 工作计划的最低难度 | 难度：困难 | 标签：数组、动态规划
 * 你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
 *
 * 你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
 *
 * 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
 *
 * 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
 *
 * 示例 1：
 * 输入：jobDifficulty = [6,5,4,3,2,1], d = 2
 * 输出：7
 * 解释：第一天，您可以完成前 5 项工作，总难度 = 6.
 * 第二天，您可以完成最后一项工作，总难度 = 1.
 * 计划表的难度 = 6 + 1 = 7
 *
 * 示例 2：
 * 输入：jobDifficulty = [9,9,9], d = 4
 * 输出：-1
 * 解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
 *
 * 示例 3：
 * 输入：jobDifficulty = [1,1,1], d = 3
 * 输出：3
 * 解释：工作计划为每天一项工作，总难度为 3 。
 *
 * 示例 4：
 * 输入：jobDifficulty = [7,1,7,1,7,1], d = 3
 * 输出：15
 *
 * 示例 5：
 * 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * 输出：843
 *
 * 提示：
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1335 {
  /**
   * 执行用时：572 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：34 / 34
   *
   * 参考题解做的
   *
   * @param jobDifficulty
   * @param d
   * @return
   */
  def minDifficulty(jobDifficulty: Array[Int], d: Int): Int = {
    val n = jobDifficulty.length
    if (n < d) return -1
    if (n == d) return jobDifficulty.sum
    val dp = Array.fill(n)(Array.fill(d + 1)(Int.MaxValue >> 1))
    dp(0)(1) = jobDifficulty(0)
    for (i <- 1 until n) {
      val max = new Array[Int](i + 1)
      max(i) = jobDifficulty(i)
      for (j <- i - 1 to 0 by -1) {
        max(j) = max(j + 1) max jobDifficulty(j)
      }
      for (j <- d min (i + 1) until 1 by -1; k <- 0 until i) {
        dp(i)(j) = dp(i)(j) min (dp(k)(j - 1) + max(k + 1))
      }
      dp(i)(1) = max(0)
    }
    dp(n - 1)(d)
  }
}
