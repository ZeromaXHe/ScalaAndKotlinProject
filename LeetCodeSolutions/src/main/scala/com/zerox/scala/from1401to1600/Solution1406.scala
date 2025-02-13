package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/4 17:35
 * @note
 * 1406. 石子游戏 III | 难度：困难 | 标签：数组、数学、动态规划、博弈
 * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
 *
 * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
 *
 * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
 *
 * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
 *
 * 示例 1：
 * 输入：values = [1,2,3,7]
 * 输出："Bob"
 * 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
 *
 * 示例 2：
 * 输入：values = [1,2,3,-9]
 * 输出："Alice"
 * 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
 * 如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
 * 如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
 * 注意，他们都应该采取 最优策略 ，所以在这里 Alice 将选择能够使她获胜的方案。
 *
 * 示例 3：
 * 输入：values = [1,2,3,6]
 * 输出："Tie"
 * 解释：Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
 *
 * 示例 4：
 * 输入：values = [1,2,3,-1,-2,-3,7]
 * 输出："Alice"
 *
 * 示例 5：
 * 输入：values = [-1,-2,-3]
 * 输出："Tie"
 *
 * 提示：
 * 1 <= values.length <= 50000
 * -1000 <= values[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stone-game-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1406 {
  /**
   * 执行用时：1076 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：185 / 185
   *
   * @param stoneValue
   * @return
   */
  def stoneGameIII(stoneValue: Array[Int]): String = {
    val n = stoneValue.length
    val dp = new Array[Int](n)
    dp(n - 1) = stoneValue(n - 1)
    if (n == 1) return valueToResultString(dp(0))
    dp(n - 2) = math.max(stoneValue(n - 1) + stoneValue(n - 2), stoneValue(n - 2) - dp(n - 1))
    if (n == 2) return valueToResultString(dp(0))
    dp(n - 3) = math.max(math.max(stoneValue(n - 1) + stoneValue(n - 2) + stoneValue(n - 3),
      stoneValue(n - 3) + stoneValue(n - 2) - dp(n - 1)),
      stoneValue(n - 3) - dp(n - 2))
    for (i <- Range(n - 4, -1, -1)) {
      dp(i) = math.max(math.max(stoneValue(i) - dp(i + 1),
        stoneValue(i) + stoneValue(i + 1) - dp(i + 2)),
        stoneValue(i) + stoneValue(i + 1) + stoneValue(i + 2) - dp(i + 3))
    }
    valueToResultString(dp(0))
  }

  private def valueToResultString(value: Int): String = {
    value match {
      case v if v > 0 => "Alice"
      case 0 => "Tie"
      case _ => "Bob"
    }
  }
}
