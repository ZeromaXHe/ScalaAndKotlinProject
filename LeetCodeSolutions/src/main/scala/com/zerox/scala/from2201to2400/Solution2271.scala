package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/8/31 14:14
 * @note
 * 2271. 毯子覆盖的最多白色砖块数 | 难度：中等 | 标签：贪心、数组、二分查找、前缀和、排序
 * 给你一个二维整数数组 tiles ，其中 tiles[i] = [li, ri] ，表示所有在 li <= j <= ri 之间的每个瓷砖位置 j 都被涂成了白色。
 *
 * 同时给你一个整数 carpetLen ，表示可以放在 任何位置 的一块毯子。
 *
 * 请你返回使用这块毯子，最多 可以盖住多少块瓷砖。
 *
 * 示例 1：
 * 输入：tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * 输出：9
 * 解释：将毯子从瓷砖 10 开始放置。
 * 总共覆盖 9 块瓷砖，所以返回 9 。
 * 注意可能有其他方案也可以覆盖 9 块瓷砖。
 * 可以看出，瓷砖无法覆盖超过 9 块瓷砖。
 *
 * 示例 2：
 * 输入：tiles = [[10,11],[1,1]], carpetLen = 2
 * 输出：2
 * 解释：将毯子从瓷砖 10 开始放置。
 * 总共覆盖 2 块瓷砖，所以我们返回 2 。
 *
 * 提示：
 * 1 <= tiles.length <= 5 * 104
 * tiles[i].length == 2
 * 1 <= li <= ri <= 109
 * 1 <= carpetLen <= 109
 * tiles 互相 不会重叠 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2271 {
  /**
   * 执行用时：1092 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：86.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：51 / 51
   *
   * 思路倒是有，只是没想到题解里面这个 extra 的操作。这样直接就可以只计算每个连续瓷砖开头。
   * 自己一直在想类似滑动窗口怎么去处理左边在连续瓷砖中间的情况，其实是处理的不够优雅。
   *
   * @param tiles
   * @param carpetLen
   * @return
   */
  def maximumWhiteTiles(tiles: Array[Array[Int]], carpetLen: Int): Int = {
    val sort = tiles.sortBy(_ (0))
    var max = 0
    var count = 0
    var j = 0
    for (i <- sort.indices) {
      if (i > 0) count -= sort(i - 1)(1) - sort(i - 1)(0) + 1
      while (j < sort.length && sort(i)(0) + carpetLen > sort(j)(1)) {
        count += sort(j)(1) - sort(j)(0) + 1
        j += 1
      }
      if (j == sort.length) {
        if (count > max) max = count
        return max
      }
      val extra = math.max(0, sort(i)(0) + carpetLen - sort(j)(0))
      if (count + extra > max) max = count + extra
    }
    max
  }
}
