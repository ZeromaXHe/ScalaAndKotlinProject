package com.zerox.kotlin.from601to800

/**
 * 733. 图像渲染 | 难度：简单 | 标签：深度优先搜索、广度优先搜索、数组、矩阵
 * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
 *
 * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
 *
 * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为 newColor 。
 *
 * 最后返回 经过上色渲染后的图像 。
 *
 * 示例 1:
 * 输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析: 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
 *
 * 示例 2:
 * 输入: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * 输出: [[2,2,2],[2,2,2]]
 *
 * 提示:
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], newColor < 216
 * 0 <= sr < m
 * 0 <= sc < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 16:29
 */
object Solution733 {
    /**
     * 执行用时：240 ms, 在所有 Kotlin 提交中击败了 70.37% 的用户
     * 内存消耗：35.6 MB, 在所有 Kotlin 提交中击败了 96.30% 的用户
     * 通过测试用例：277 / 277
     */
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        if (image[sr][sc] != color) {
            dfs(image, sr, sc, image[sr][sc], color)
        }
        return image
    }

    private fun dfs(image: Array<IntArray>, i: Int, j: Int, from: Int, to: Int) {
        image[i][j] = to
        if (i - 1 >= 0 && image[i - 1][j] == from) dfs(image, i - 1, j, from, to)
        if (j - 1 >= 0 && image[i][j - 1] == from) dfs(image, i, j - 1, from, to)
        if (i + 1 < image.size && image[i + 1][j] == from) dfs(image, i + 1, j, from, to)
        if (j + 1 < image[0].size && image[i][j + 1] == from) dfs(image, i, j + 1, from, to)
    }
}