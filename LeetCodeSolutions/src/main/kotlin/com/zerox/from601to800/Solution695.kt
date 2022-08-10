package com.zerox.from601to800

/**
 * 695. 岛屿的最大面积 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 *
 * 示例 2：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 14:35
 */
object Solution695 {
    /**
     * 执行用时：192 ms, 在所有 Kotlin 提交中击败了 90.48% 的用户
     * 内存消耗：42.4 MB, 在所有 Kotlin 提交中击败了 9.52% 的用户
     * 通过测试用例：728 / 728
     */
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var max = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j))
                }
            }
        }
        return max
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int): Int {
        grid[i][j] = 0
        var count = 1
        if (i - 1 >= 0 && grid[i - 1][j] == 1) count += dfs(grid, i - 1, j)
        if (j - 1 >= 0 && grid[i][j - 1] == 1) count += dfs(grid, i, j - 1)
        if (i + 1 < grid.size && grid[i + 1][j] == 1) count += dfs(grid, i + 1, j)
        if (j + 1 < grid[0].size && grid[i][j + 1] == 1) count += dfs(grid, i, j + 1)
        return count
    }
}