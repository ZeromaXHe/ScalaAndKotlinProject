package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/4 11:39
 * @note
 * 2322. 从树中删除边的最小分数 | 难度：困难 | 标签：位运算、树、深度优先搜索、数组
 * 存在一棵无向连通树，树中有编号从 0 到 n - 1 的 n 个节点， 以及 n - 1 条边。
 *
 * 给你一个下标从 0 开始的整数数组 nums ，长度为 n ，其中 nums[i] 表示第 i 个节点的值。另给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中存在一条位于节点 ai 和 bi 之间的边。
 *
 * 删除树中两条 不同 的边以形成三个连通组件。对于一种删除边方案，定义如下步骤以计算其分数：
 *
 * 分别获取三个组件 每个 组件中所有节点值的异或值。
 * 最大 异或值和 最小 异或值的 差值 就是这一种删除边方案的分数。
 * 例如，三个组件的节点值分别是：[4,5,7]、[1,9] 和 [3,3,3] 。三个异或值分别是 4 ^ 5 ^ 7 = 6、1 ^ 9 = 8 和 3 ^ 3 ^ 3 = 3 。最大异或值是 8 ，最小异或值是 3 ，分数是 8 - 3 = 5 。
 * 返回在给定树上执行任意删除边方案可能的 最小 分数。
 *
 * 示例 1：
 * 输入：nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
 * 输出：9
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [1,3,4] ，值是 [5,4,11] 。异或值是 5 ^ 4 ^ 11 = 10 。
 * - 第 2 个组件的节点是 [0] ，值是 [1] 。异或值是 1 = 1 。
 * - 第 3 个组件的节点是 [2] ，值是 [5] 。异或值是 5 = 5 。
 * 分数是最大异或值和最小异或值的差值，10 - 1 = 9 。
 * 可以证明不存在分数比 9 小的删除边方案。
 *
 * 示例 2：
 * 输入：nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
 * 输出：0
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [3,4] ，值是 [4,4] 。异或值是 4 ^ 4 = 0 。
 * - 第 2 个组件的节点是 [1,0] ，值是 [5,5] 。异或值是 5 ^ 5 = 0 。
 * - 第 3 个组件的节点是 [2,5] ，值是 [2,2] 。异或值是 2 ^ 2 = 0 。
 * 分数是最大异或值和最小异或值的差值，0 - 0 = 0 。
 * 无法获得比 0 更小的分数 0 。
 *
 * 提示：
 * n == nums.length
 * 3 <= n <= 1000
 * 1 <= nums[i] <= 108
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2322 {
  /**
   * 参考题解 https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/solution/by-newhar-imbx/ 做的
   * 变量命名换掉了，原来的实在是看不懂……
   *
   * 执行用时：2800 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：59.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：65 / 65
   *
   * @param nums
   * @param edges
   * @return
   */
  def minimumScore(nums: Array[Int], edges: Array[Array[Int]]): Int = {
    val n = nums.size
    val connectMap = edges.flatMap(e => List(Array(e(1), e(0)), Array(e(0), e(1))))
      .groupBy(e => e(0))
      .mapValues(arr => arr.map(e => e(1)))
    var all = 0
    nums.foreach(all ^= _)

    var res = 2e9.toInt
    // 对应索引为根节点的子树的异或和
    val xorSumWithRoot = new Array[Int](n)

    def dfs(node: Int, parent: Int): Unit = {
      xorSumWithRoot(node) = nums(node)
      for (connect <- connectMap(node) if connect != parent) {
        dfs(connect, node)
        xorSumWithRoot(node) ^= xorSumWithRoot(connect)
      }
    }

    def get(node: Int, parent: Int, rootXorSum: Int): Unit = {
      for (connect <- connectMap(node) if connect != parent) {
        // 这样去计算删除边的操作，感觉还是很妙的
        val subXorSum1 = all ^ rootXorSum
        val subXorSum2 = rootXorSum ^ xorSumWithRoot(connect)
        val subXorSum3 = xorSumWithRoot(connect)
        res = math.min(res, math.max(math.max(subXorSum1, subXorSum2), subXorSum3)
          - math.min(math.min(subXorSum1, subXorSum2), subXorSum3))
        get(connect, node, rootXorSum)
      }
    }

    for (i <- nums.indices) {
      dfs(i, -1)
      for (connect <- connectMap(i)) {
        get(connect, i, xorSumWithRoot(connect))
      }
    }
    res
  }
}
