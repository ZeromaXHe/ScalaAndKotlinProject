package com.zerox.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/7/2 14:29
 * @note
 * 871. 最低加油次数 | 难度：困难 | 标签：贪心、数组、动态规划、堆（优先队列）
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 *
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 *
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 *
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 *
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 *
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 *
 * 示例 1：
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 *
 * 示例 2：
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 *
 * 示例 3：
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 *
 * 提示：
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-refueling-stops
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution871 {
  /**
   * 执行用时：600 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：198 / 198
   *
   * 参考官方题解动态规划做的
   * 还可以用贪心，不过需要配合堆（优先队列），我就没写了
   *
   * @param target
   * @param startFuel
   * @param stations
   * @return
   */
  def minRefuelStops(target: Int, startFuel: Int, stations: Array[Array[Int]]): Int = {
    // 代表停靠索引数量的加油站可以到达的最远距离
    val dp = new Array[Long](stations.length + 1)
    dp(0) = startFuel
    for (i <- stations.indices;
         j <- (0 to i).reverse
         if dp(j) >= stations(i)(0)) {
      dp(j + 1) = math.max(dp(j + 1), dp(j) + stations(i)(1))
    }
    for (i <- dp.indices
         if dp(i) >= target) {
      return i
    }
    -1
  }
}
