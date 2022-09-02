package com.zerox.scala.from1001to1200

/**
 * @author zhuxi
 * @since 2022/9/2 16:09
 * @note
 * 1140. 石子游戏 II | 难度：中等 | 标签：数组、数学、动态规划、博弈
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 *
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 *
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 *
 * 游戏一直持续到所有石子都被拿走。
 *
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 *
 * 示例 1：
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 *
 * 示例 2:
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 *
 * 提示：
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stone-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1140 {
  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：92 / 92
   *
   * 参考题解做的。虽然感觉 minmax 的思想大概明白，但感觉这个倒推的实现和 dp 数组的大小我还不一定一时半会想得出来
   *
   * @param piles
   * @return
   */
  def stoneGameII(piles: Array[Int]): Int = {
    val n = piles.length
    var sum = 0
    // dp(i)(j) 表示剩余 [i : len - 1] 堆时，M = j的情况下，先取的人能获得的最多石子数
    val dp = Array.ofDim[Int](n, n + 1)
    for (i <- piles.indices.reverse) {
      sum += piles(i)
      for (j <- 1 to n) {
        // 剩下的堆数能够直接全部取走
        if (i + 2 * j >= n) dp(i)(j) = sum
        // 让对方拿的少，即 dp(i + k)(j max k) 最小
        else for (k <- 1 to 2 * j; tmp = sum - dp(i + k)(j max k) if tmp > dp(i)(j)) {
          dp(i)(j) = tmp
        }
      }
    }
    dp(0)(1)
  }
}
