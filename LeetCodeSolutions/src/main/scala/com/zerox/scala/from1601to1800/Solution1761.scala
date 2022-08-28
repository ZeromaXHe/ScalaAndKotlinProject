package com.zerox.scala.from1601to1800

/**
 * @author ZeromaXHe
 * @since 2022/8/28 9:32
 * @note
 * 1761. 一个图中连通三元组的最小度数 | 难度：困难 | 标签：图
 * 给你一个无向图，整数 n 表示图中节点的数目，edges 数组表示图中的边，其中 edges[i] = [ui, vi] ，表示 ui 和 vi 之间有一条无向边。
 *
 * 一个 连通三元组 指的是 三个 节点组成的集合且这三个点之间 两两 有边。
 *
 * 连通三元组的度数 是所有满足此条件的边的数目：一个顶点在这个三元组内，而另一个顶点不在这个三元组内。
 *
 * 请你返回所有连通三元组中度数的 最小值 ，如果图中没有连通三元组，那么返回 -1 。
 *
 * 示例 1：
 * 输入：n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * 输出：3
 * 解释：只有一个三元组 [1,2,3] 。构成度数的边在上图中已被加粗。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * 输出：0
 * 解释：有 3 个三元组：
 * 1) [1,4,3]，度数为 0 。
 * 2) [2,5,6]，度数为 2 。
 * 3) [5,6,7]，度数为 2 。
 *
 * 提示：
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 图中没有重复的边。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1761 {
  /**
   * 执行用时：4740 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：219.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：68 / 68
   *
   * @param n
   * @param edges
   * @return
   */
  def minTrioDegree(n: Int, edges: Array[Array[Int]]): Int = {
    val map = new scala.collection.mutable.HashMap[Int, scala.collection.mutable.HashSet[Int]]
    for (e <- edges) {
      if (!map.contains(e(0))) map(e(0)) = new scala.collection.mutable.HashSet[Int]
      map(e(0)).add(e(1))
      if (!map.contains(e(1))) map(e(1)) = new scala.collection.mutable.HashSet[Int]
      map(e(1)).add(e(0))
    }
    val trioMap = Array.ofDim[Boolean](n + 1, n + 1, n + 1)
    var min = -1
    for (e <- edges; tri <- map(e(0)) intersect map(e(1)) if !trioMap(e(0))(e(1))(tri)) {
      val count = map(tri).size + map(e(0)).size + map(e(1)).size - 6
      if (count == 0) return 0
      if (count < min || min == -1) min = count
      trioMap(e(0))(e(1))(tri) = true
      trioMap(e(1))(e(0))(tri) = true
      trioMap(e(0))(tri)(e(1)) = true
      trioMap(e(1))(tri)(e(0)) = true
      trioMap(tri)(e(0))(e(1)) = true
      trioMap(tri)(e(1))(e(0)) = true
    }
    min
  }
}
