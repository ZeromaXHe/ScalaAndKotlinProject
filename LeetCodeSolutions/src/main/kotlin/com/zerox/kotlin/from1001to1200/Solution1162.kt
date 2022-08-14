package com.zerox.kotlin.from1001to1200

/**
 * 1162. 地图分析 | 难度：中等 | 标签：广度优先搜索、数组、动态规划、矩阵
 * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。
 *
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 *
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 * 示例 1：
 * 输入：grid = [[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 *
 * 示例 2：
 * 输入：grid = [[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 不是 0 就是 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/as-far-from-land-as-possible
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 10:06
 */
object Solution1162 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(maxDistance(arrayOf(intArrayOf(1, 0, 1), intArrayOf(0, 0, 0), intArrayOf(1, 0, 1))))
    }

    /**
     * 执行用时：304 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：45.7 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：37 / 37
     */
    fun maxDistance(grid: Array<IntArray>): Int {
        // 力扣的 Kotlin MutableList 居然没有 removeFirst…… Kotlin 的集合类库真是用的让人恼火，啥都没
        val queue = java.util.LinkedList<Pair<Int, Int>>()
        putAllEdgeLandsIntoQueue(queue, grid)
        var dist = -1
        while (queue.isNotEmpty()) {
            var c = queue.size
            while (c > 0) {
                val (x, y) = queue.poll()
                if (x - 1 >= 0 && grid[x - 1][y] == 0) {
                    queue.offer(Pair(x - 1, y))
                    grid[x - 1][y] = -1
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 0) {
                    queue.offer(Pair(x, y - 1))
                    grid[x][y - 1] = -1
                }
                if (x + 1 < grid.size && grid[x + 1][y] == 0) {
                    queue.offer(Pair(x + 1, y))
                    grid[x + 1][y] = -1
                }
                if (y + 1 < grid[0].size && grid[x][y + 1] == 0) {
                    queue.offer(Pair(x, y + 1))
                    grid[x][y + 1] = -1
                }
                c--
            }
            dist++
        }
        return dist
    }

    private fun putAllEdgeLandsIntoQueue(queue: java.util.LinkedList<Pair<Int, Int>>, grid: Array<IntArray>) {
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue)
                }
            }
        }
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int, queue: java.util.LinkedList<Pair<Int, Int>>) {
        grid[i][j] = 2
        if (i - 1 >= 0) {
            if (grid[i - 1][j] == 1) dfs(grid, i - 1, j, queue)
            else if (grid[i - 1][j] == 0) grid[i][j] = 3
        }
        if (j - 1 >= 0) {
            if (grid[i][j - 1] == 1) dfs(grid, i, j - 1, queue)
            else if (grid[i][j - 1] == 0) grid[i][j] = 3
        }
        if (i + 1 < grid.size) {
            if (grid[i + 1][j] == 1) dfs(grid, i + 1, j, queue)
            else if (grid[i + 1][j] == 0) grid[i][j] = 3
        }
        if (j + 1 < grid[0].size) {
            if (grid[i][j + 1] == 1) dfs(grid, i, j + 1, queue)
            else if (grid[i][j + 1] == 0) grid[i][j] = 3
        }
        if (grid[i][j] == 3) queue.offer(Pair(i, j))
    }
}