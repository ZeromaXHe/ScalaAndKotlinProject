package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/4 14:33
 * @note
 * 2316. 统计无向图中无法互相到达点对数 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、图
 * 给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。
 *
 * 请你返回 无法互相到达 的不同 点对数目 。
 *
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[0,2],[1,2]]
 * 输出：0
 * 解释：所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * 输出：14
 * 解释：总共有 14 个点对互相无法到达：
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
 * 所以我们返回 14 。
 *
 * 提示：
 * 1 <= n <= 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 不会有重复边。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2316 {
  def main(args: Array[String]): Unit = {
    println(countPairs(7, Array(Array(0, 2), Array(0, 5), Array(2, 4), Array(1, 6), Array(5, 4))) /* == 14*/)
    println(countPairs(6, Array(Array(0, 1), Array(2, 3), Array(4, 5))) /* == 12*/)
    println(countPairs(11, Array(Array(5, 0), Array(1, 0), Array(10, 7), Array(9, 8), Array(7, 2), Array(1, 3),
      Array(0, 2), Array(8, 5), Array(4, 6), Array(4, 2))) == 0)
  }

  /**
   * 执行用时：1564 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：129.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：66 / 66
   *
   * 要是真在周赛做这个怕不是要凉，做了好久，最后还是看题解解决了用 find 刷新 parent 的问题
   *
   * @param n
   * @param edges
   * @return
   */
  def countPairs(n: Int, edges: Array[Array[Int]]): Long = {
    val unionSet = new UnionSet(n)
    for (e <- edges) {
      unionSet.union(e(0), e(1))
    }
    val parentSet = new scala.collection.mutable.HashSet[Int]
    (for (i <- 0 until n; parent = unionSet.find(i) if !parentSet(parent)) yield {
      parentSet += parent
      val size = unionSet.getSize(parent)
      size.toLong * (n - size)
    }).sum / 2
  }

  class UnionSet(n: Int) {
    private val parent = new Array[Int](n)
    private val size = Array.fill(n)(1)

    for (i <- 0 until n) {
      parent(i) = i
    }

    def find(x: Int): Int = {
      if (x != parent(x)) parent(x) = find(parent(x))
      parent(x)
    }

    def union(x: Int, y: Int): Unit = {
      val rootX = find(x)
      val rootY = find(y)
      if (rootX == rootY) return
      val big = if (size(rootX) < size(rootY)) rootY else rootX
      val small = if (size(rootX) < size(rootY)) rootX else rootY
      parent(small) = big
      size(big) += size(small)
    }

    def getSize(x: Int): Int = {
      size(x)
    }
  }
}
