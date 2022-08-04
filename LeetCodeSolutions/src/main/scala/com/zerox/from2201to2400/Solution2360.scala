package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/8/4 10:44
 * @note
 * 2360. 图中的最长环 | 难度：困难 | 标签：
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
 *
 * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
 *
 * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
 *
 * 一个环指的是起点和终点是 同一个 节点的路径。
 *
 * 示例 1：
 * 输入：edges = [3,3,4,2,3]
 * 输出去：3
 * 解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
 * 这个环的长度为 3 ，所以返回 3 。
 *
 * 示例 2：
 * 输入：edges = [2,-1,3,1]
 * 输出：-1
 * 解释：图中没有任何环。
 *
 * 提示：
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-cycle-in-a-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2360 {
  /**
   * 执行用时：972 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：81.7 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：76 / 76
   *
   * @param edges
   * @return
   */
  def longestCycle(edges: Array[Int]): Int = {
    var result = -1
    val visit = new Array[Int](edges.length)
    var idx = 1
    for (i <- edges.indices if visit(i) == 0) {
      val fromIdx = idx
      var ptr = i
      var continue = true
      while (continue && ptr >= 0) {
        if (visit(ptr) == 0) {
          visit(ptr) = idx
          idx += 1
          ptr = edges(ptr)
        } else {
          continue = false
        }
      }
      if (ptr >= 0 && visit(ptr) >= fromIdx) {
        result = math.max(result, idx - visit(ptr))
      }
    }
    result
  }
}
