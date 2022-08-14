package com.zerox.kotlin.from401to600

/**
 * 417. 太平洋大西洋水流问题 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、数组、矩阵
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 *
 * 示例 1：
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * 示例 2：
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 10:42
 */
object Solution417 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(pacificAtlantic(arrayOf(intArrayOf(2,1), intArrayOf(1,2))))
    }

    /**
     * 执行用时：312 ms, 在所有 Kotlin 提交中击败了 80.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：113 / 113
     */
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size
        val belongs = Array(m) { IntArray(n) }
        for (i in heights.indices) {
            dfs(heights, i, 0, belongs, 1)
            dfs(heights, i, n - 1, belongs, 2)
        }
        for (j in heights[0].indices) {
            dfs(heights, 0, j, belongs, 1)
            dfs(heights, m - 1, j, belongs, 2)
        }
        val result = mutableListOf<List<Int>>()
        for (i in belongs.indices) {
            for (j in belongs[0].indices) {
                if (belongs[i][j] == 3) result.add(listOf(i, j))
            }
        }
        return result
    }

    private fun dfs(heights: Array<IntArray>, i: Int, j: Int, belongs: Array<IntArray>, add: Int) {
        if (belongs[i][j] and add > 0) return
        belongs[i][j] += add
        if (i - 1 >= 0 && heights[i - 1][j] >= heights[i][j]) dfs(heights, i - 1, j, belongs, add)
        if (j - 1 >= 0 && heights[i][j - 1] >= heights[i][j]) dfs(heights, i, j - 1, belongs, add)
        if (i + 1 < heights.size && heights[i + 1][j] >= heights[i][j]) dfs(heights, i + 1, j, belongs, add)
        if (j + 1 < heights[0].size && heights[i][j + 1] >= heights[i][j]) dfs(heights, i, j + 1, belongs, add)
    }
}