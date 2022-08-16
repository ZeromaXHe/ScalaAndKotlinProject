package com.zerox.kotlin.from1201to1400

/**
 * 1319. 连通网络的操作次数 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、图
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 *
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 *
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 
 *
 * 示例 1：
 * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
 * 输出：1
 * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
 *
 * 示例 2：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * 输出：2
 *
 * 示例 3：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * 输出：-1
 * 解释：线缆数量不足。
 *
 * 示例 4：
 * 输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * 输出：0
 *
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * 没有重复的连接。
 * 两台计算机不会通过多条线缆连接。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-operations-to-make-network-connected
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 10:02
 */
object Solution1319 {
    /**
     * 执行用时：360 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：71.4 MB, 在所有 Kotlin 提交中击败了 80.00% 的用户
     * 通过测试用例：36 / 36
     */
    fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n - 1) return -1
        val unionSet = UnionSet(n)
        connections.forEach { unionSet.union(it[0], it[1]) }
        return unionSet.getCount() - 1
    }

    class UnionSet(n: Int) {
        /**
         * 当前结点的父亲结点
         */
        private val parent = IntArray(n) { i -> i }

//        /**
//         * 以当前结点为根结点的子树的结点总数
//         */
//        private val size = IntArray(n) { 1 }

        /**
         * 集合数量
         */
        private var count = n

        /**
         * 路径压缩，只要求每个不相交集合的「根结点」的子树包含的结点总数数值正确即可，因此在路径压缩的过程中不用维护数组 size
         *
         * @param x
         * @return
         */
        fun find(x: Int): Int {
            if (x != parent[x]) parent[x] = find(parent[x])
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX == rootY) return
            parent[rootX] = rootY
            // 在合并的时候维护数组 size
//            size[rootY] += size[rootX]
            count--
        }

//        /**
//         * @param x
//         * @return x 在并查集的根结点的子树包含的结点总数
//         */
//        fun getSize(x: Int): Int {
//            val root = find(x)
//            return size[root]
//        }

        fun getCount(): Int {
            return count
        }
    }
}