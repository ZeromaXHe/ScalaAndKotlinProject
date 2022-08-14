package com.zerox.kotlin.from401to600

/**
 * 547. 省份数量 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、图
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 16:44
 */
object Solution547 {
    /**
     * 执行用时：356 ms, 在所有 Kotlin 提交中击败了 7.69% 的用户
     * 内存消耗：43.4 MB, 在所有 Kotlin 提交中击败了 15.38% 的用户
     * 通过测试用例：113 / 113
     */
    fun findCircleNum_map(isConnected: Array<IntArray>): Int {
        val map = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until isConnected.size - 1) {
            for (j in i + 1 until isConnected.size) {
                if (isConnected[i][j] == 1) {
                    map[i]?.add(j) ?: run { map[i] = mutableListOf(j) }
                    map[j]?.add(i) ?: run { map[j] = mutableListOf(i) }
                }
            }
        }
        val visit = BooleanArray(isConnected.size)
        var res = 0
        for (i in isConnected.indices) {
            if (!visit[i]) {
                dfs(map, visit, i)
                res++
            }
        }
        return res
    }

    private fun dfs(map: Map<Int, List<Int>>, visit: BooleanArray, idx: Int) {
        visit[idx] = true
        map[idx]?.forEach {
            if (!visit[it]) {
                dfs(map, visit, it)
            }
        }
    }

    /**
     * 执行用时：248 ms, 在所有 Kotlin 提交中击败了 30.77% 的用户
     * 内存消耗：42.3 MB, 在所有 Kotlin 提交中击败了 46.15% 的用户
     * 通过测试用例：113 / 113
     */
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val visit = BooleanArray(isConnected.size)
        var res = 0
        for (i in isConnected.indices) {
            if (!visit[i]) {
                dfs(isConnected, visit, i)
                res++
            }
        }
        return res
    }

    private fun dfs(isConnected: Array<IntArray>, visit: BooleanArray, idx: Int) {
        visit[idx] = true
        for (j in isConnected.indices) {
            if (!visit[j] && isConnected[idx][j] == 1) {
                dfs(isConnected, visit, j)
            }
        }
    }
}