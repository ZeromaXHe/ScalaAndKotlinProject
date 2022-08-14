package com.zerox.kotlin.from1201to1400

/**
 * 1254. 统计封闭岛屿的数目 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 *
 * 请返回 封闭岛屿 的数目。
 *
 * 示例 1：
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 *
 * 示例 2：
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1,1,1],
 *              [1,0,0,0,0,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,1,0,1,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * 输出：2
 *
 * 提示：
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-closed-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 14:42
 */
object Solution1254 {
    /**
     * 执行用时：184 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：47 / 47
     */
    fun closedIsland(grid: Array<IntArray>): Int {
        var result = 0
        for (i in grid.indices) {
            if (grid[i][0] == 0) dfs(grid, i, 0)
            if (grid[i][grid[0].size - 1] == 0) dfs(grid, i, grid[0].size - 1)
        }
        for (j in 1..grid[0].size - 2) {
            if (grid[0][j] == 0) dfs(grid, 0, j)
            if (grid[grid.size - 1][j] == 0) dfs(grid, grid.size - 1, j)
        }
        for (i in 1..grid.size - 2) {
            for (j in 1..grid[0].size - 2) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j)
                    result++
                }
            }
        }
        return result
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int) {
        grid[i][j] = 1
        if (i - 1 >= 0 && grid[i - 1][j] == 0) dfs(grid, i - 1, j)
        if (j - 1 >= 0 && grid[i][j - 1] == 0) dfs(grid, i, j - 1)
        if (i + 1 < grid.size && grid[i + 1][j] == 0) dfs(grid, i + 1, j)
        if (j + 1 < grid[0].size && grid[i][j + 1] == 0) dfs(grid, i, j + 1)
    }
}