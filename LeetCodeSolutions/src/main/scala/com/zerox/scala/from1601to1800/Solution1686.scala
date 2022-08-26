package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/8/26 17:29
 * @note
 * 1686. 石子游戏 VI | 难度：中等 | 标签：贪心、数组、数学、博弈、排序、堆（优先队列）
 * Alice 和 Bob 轮流玩一个游戏，Alice 先手。
 *
 * 一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 不一样的的评判标准 。双方都知道对方的评判标准。
 *
 * 给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob 认为第 i 个石子的价值。
 *
 * 所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 最优策略 进行游戏。
 *
 * 请你推断游戏的结果，用如下的方式表示：
 *
 * 如果 Alice 赢，返回 1 。
 * 如果 Bob 赢，返回 -1 。
 * 如果游戏平局，返回 0 。
 *
 * 示例 1：
 * 输入：aliceValues = [1,3], bobValues = [2,1]
 * 输出：1
 * 解释：
 * 如果 Alice 拿石子 1 （下标从 0开始），那么 Alice 可以得到 3 分。
 * Bob 只能选择石子 0 ，得到 2 分。
 * Alice 获胜。
 *
 * 示例 2：
 * 输入：aliceValues = [1,2], bobValues = [3,1]
 * 输出：0
 * 解释：
 * Alice 拿石子 0 ， Bob 拿石子 1 ，他们得分都为 1 分。
 * 打平。
 *
 * 示例 3：
 * 输入：aliceValues = [2,4,3], bobValues = [1,6,7]
 * 输出：-1
 * 解释：
 * 不管 Alice 怎么操作，Bob 都可以得到比 Alice 更高的得分。
 * 比方说，Alice 拿石子 1 ，Bob 拿石子 2 ， Alice 拿石子 0 ，Alice 会得到 6 分而 Bob 得分为 7 分。
 * Bob 会获胜。
 *
 * 提示：
 * n == aliceValues.length == bobValues.length
 * 1 <= n <= 105
 * 1 <= aliceValues[i], bobValues[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stone-game-vi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1686 {
  /**
   * 执行用时：1440 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：77.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：95 / 95
   *
   * @param aliceValues
   * @param bobValues
   * @return
   */
  def stoneGameVI(aliceValues: Array[Int], bobValues: Array[Int]): Int = {
    val sort = (aliceValues zip bobValues).sortBy(t => t._1 + t._2)
    var sumA = 0
    var sumB = 0
    for (i <- sort.indices.reverse) {
      if ((sort.length - i) % 2 == 1) sumA += sort(i)._1
      else sumB += sort(i)._2
    }
    sumA.compareTo(sumB)
  }
}
