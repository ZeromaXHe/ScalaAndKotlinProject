package com.zerox.kotlin.from601to800

/**
 * 797. 所有可能的路径 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、回溯
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 *
 *  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 *
 * 示例 1：
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 *
 * 示例 2：
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 * 提示：
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i（即不存在自环）
 * graph[i] 中的所有元素 互不相同
 * 保证输入为 有向无环图（DAG）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 10:14
 */
object Solution797 {
    /**
     * 执行用时：260 ms, 在所有 Kotlin 提交中击败了 90.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：30 / 30
     */
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val temp = mutableListOf<Int>()
        val target = graph.size - 1
        dfs(graph, 0, target, result, temp)
        return result
    }

    private fun dfs(
        graph: Array<IntArray>, now: Int, target: Int,
        result: MutableList<List<Int>>, temp: MutableList<Int>
    ) {
        temp.add(now)
        if (now == target) {
            result.add(temp.toList())
        } else {
            val nexts = graph[now]
            for (next in nexts) {
                dfs(graph, next, target, result, temp)
            }
        }
        temp.removeAt(temp.size - 1)
        // 力扣 Kotlin 1.3 没有 removeLast
//        temp.removeLast()
    }
}