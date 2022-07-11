package com.zerox.from2201to2400

/**
 * @author ZeromaXHe
 * @since 2022/7/2 21:57
 * @note
 * 2320. 统计放置房子的方式数 | 难度：中等 | 标签：动态规划
 * 一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。每一边的地块都按从 1 到 n 编号。每个地块上都可以放置一所房子。
 *
 * 现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。由于答案可能很大，需要对 109 + 7 取余后再返回。
 *
 * 注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：4
 * 解释：
 * 可能的放置方式：
 * 1. 所有地块都不放置房子。
 * 2. 一所房子放在街道的某一侧。
 * 3. 一所房子放在街道的另一侧。
 * 4. 放置两所房子，街道两侧各放置一所。
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：9
 * 解释：如上图所示，共有 9 种可能的放置方式。
 *
 * 提示：
 * 1 <= n <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-number-of-ways-to-place-houses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2320 {
  /**
   * 执行用时：444 ms, 在所有 Scala 提交中击败了 80.00% 的用户
   * 内存消耗：51.5 MB, 在所有 Scala 提交中击败了 20.00% 的用户
   * 通过测试用例：150 / 150
   *
   * @param n
   * @return
   */
  def countHousePlacements(n: Int): Int = {
    if (n == 1) return 4
    val empty = new Array[Int](n + 1)
    val fill = new Array[Int](n + 1)
    fill(1) = 1
    empty(1) = 1
    for (i <- 2 to n) {
      fill(i) = empty(i - 1)
      empty(i) = (fill(i - 1) + empty(i - 1)) % 1000000007
    }
    val dp = (empty(n).toLong + fill(n)) % 1000000007
    (dp * dp % 1000000007).toInt
  }
}
