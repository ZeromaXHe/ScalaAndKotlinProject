package com.zerox.kotlin.from1to200

/**
 * 130. 被围绕的区域 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 14:02
 */
object Solution130 {
    /**
     * 执行用时：216 ms, 在所有 Kotlin 提交中击败了 81.25% 的用户
     * 内存消耗：37.1 MB, 在所有 Kotlin 提交中击败了 68.75% 的用户
     * 通过测试用例：58 / 58
     */
    fun solve(board: Array<CharArray>): Unit {
        for (i in board.indices) {
            if (board[i][0] == 'O') dfs(board, i, 0) { g, x, y -> g[x][y] = 'o' }
            if (board[i][board[0].size - 1] == 'O')
                dfs(board, i, board[0].size - 1) { g, x, y -> g[x][y] = 'o' }
        }
        for (j in 1..board[0].size - 2) {
            if (board[0][j] == 'O') dfs(board, 0, j) { g, x, y -> g[x][y] = 'o' }
            if (board[board.size - 1][j] == 'O')
                dfs(board, board.size - 1, j) { g, x, y -> g[x][y] = 'o' }
        }
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] == 'o') board[i][j] = 'O'
                else if (board[i][j] == 'O') board[i][j] = 'X'
            }
        }
    }

    private fun dfs(grid: Array<CharArray>, i: Int, j: Int, exec: (Array<CharArray>, Int, Int) -> Unit): Unit {
        exec(grid, i, j)
        if (i - 1 >= 0 && grid[i - 1][j] == 'O') dfs(grid, i - 1, j, exec)
        if (j - 1 >= 0 && grid[i][j - 1] == 'O') dfs(grid, i, j - 1, exec)
        if (i + 1 < grid.size && grid[i + 1][j] == 'O') dfs(grid, i + 1, j, exec)
        if (j + 1 < grid[0].size && grid[i][j + 1] == 'O') dfs(grid, i, j + 1, exec)
    }
}