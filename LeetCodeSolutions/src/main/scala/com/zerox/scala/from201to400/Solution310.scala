package com.zerox.scala.from201to400

import com.zerox.scala.aider.GraphUtils.topologicalSort

/**
 * @author zhuxi
 * @since 2022/9/7 10:49
 * @note
 * 310. 最小高度树 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、拓扑排序
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 *
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 *
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 *
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 *
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 *
 * 示例 2：
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *
 * 提示：
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入 保证 是一棵树，并且 不会有重复的边
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-height-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution310 {
  def main(args: Array[String]): Unit = {
    println(findMinHeightTrees(6, Array(Array(0, 1), Array(0, 2), Array(0, 3), Array(3, 4), Array(4, 5)))) // [3]
  }

  /**
   * 执行用时：996 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：70.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：71 / 71
   *
   * @param n
   * @param edges
   * @return
   */
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    if (n == 1) return List(0)
    val ts = topologicalSort(n, edges, dir = false, condition = _ == 1)
    if (ts.size >= 2) {
      val secondLast = ts(ts.length - 2)
      val last = ts.last
      if (secondLast.size + last.size <= 2) secondLast ::: last
      else last
    }
    else if (ts.size == 1) ts.last
    else List.empty
  }

  /**
   * 执行用时：980 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：68.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：71 / 71
   *
   * @param n
   * @param edges
   * @return
   */
  def findMinHeightTrees_noTemplate(n: Int, edges: Array[Array[Int]]): List[Int] = {
    if (n == 1) return List(0)
    val degree = new Array[Int](n)
    import scala.collection.mutable
    val map = new mutable.HashMap[Int, mutable.HashSet[Int]]
    for (edge <- edges) {
      if (!map.contains(edge(0))) map(edge(0)) = new mutable.HashSet[Int]
      if (!map.contains(edge(1))) map(edge(1)) = new mutable.HashSet[Int]
      map(edge(0)) += edge(1)
      map(edge(1)) += edge(0)
      degree(edge(0)) += 1
      degree(edge(1)) += 1
    }
    val queue = new mutable.Queue[Int]
    for (i <- degree.indices if degree(i) == 1) queue.enqueue(i)
    var remain = n
    while (remain > 2) {
      var size = queue.size
      remain -= size
      for (_ <- 0 until size) {
        val deq = queue.dequeue()
        for (v <- map(deq)) {
          degree(v) -= 1
          if (degree(v) == 1) queue.enqueue(v)
        }
      }
    }
    queue.toList
  }
}
