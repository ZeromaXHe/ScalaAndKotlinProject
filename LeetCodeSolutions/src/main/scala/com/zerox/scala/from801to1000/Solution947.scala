package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/9/2 15:23
 * @note
 * 947. 移除最多的同行或同列石头 | 难度：中等 | 标签：深度优先搜索、并查集、图
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 *
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 *
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution947 {
  /**
   * 执行用时：600 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：68 / 68
   *
   * @param stones
   * @return
   */
  def removeStones(stones: Array[Array[Int]]): Int = {
    import scala.collection.mutable
    val rowMap = new mutable.HashMap[Int, Int]
    val colMap = new mutable.HashMap[Int, Int]
    val unionSet = new UnionSet(stones.length)
    for (i <- stones.indices; stone = stones(i)) {
      if (!rowMap.contains(stone(0))) rowMap(stone(0)) = i
      else unionSet.union(rowMap(stone(0)), i)
      if (!colMap.contains(stone(1))) colMap(stone(1)) = i
      else unionSet.union(colMap(stone(1)), i)
    }
    stones.length - unionSet.getCount()
  }


  class UnionSet(n: Int) {
    private val parent = Array.tabulate(n)(identity)
    private val size = Array.fill(n)(1)
    private var count = n

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
      count -= 1
    }

    def getCount(): Int = {
      count
    }
  }
}
