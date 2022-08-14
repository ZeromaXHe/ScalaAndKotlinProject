package com.zerox.kotlin.from1to200

/**
 * 59. 螺旋矩阵 II | 难度：中等 | 标签：
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 14:36
 */
object Solution59 {
    /**
     * 执行用时：148 ms, 在所有 Kotlin 提交中击败了 61.29% 的用户
     * 内存消耗：33.2 MB, 在所有 Kotlin 提交中击败了 32.26% 的用户
     * 通过测试用例：20 / 20
     *
     * 一轮循环里面直接走完四个边。不要引入 dir 变量然后 when 走四个分支，不然耗时 200ms。
     */
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array(n) { IntArray(n) }
        var minI = 1
        var maxI = n - 1
        var minJ = 0
        var maxJ = n - 1
        var i = 0
        var j = -1
        var count = 0
        while (count < n * n) {
            while (count < n * n && j < maxJ) {
                j++
                result[i][j] = ++count
            }
            maxJ--
            while (count < n * n && i < maxI) {
                i++
                result[i][j] = ++count
            }
            maxI--
            while (count < n * n && j > minJ) {
                j--
                result[i][j] = ++count
            }
            minJ++
            while (count < n * n && i > minI) {
                i--
                result[i][j] = ++count
            }
            minI++
        }
        return result
    }

    /**
     * 执行用时：152 ms, 在所有 Kotlin 提交中击败了 41.94% 的用户
     * 内存消耗：33 MB, 在所有 Kotlin 提交中击败了 93.55% 的用户
     * 通过测试用例：20 / 20
     */
    fun generateMatrix_calc(n: Int): Array<IntArray> {
        val matrix = Array(n) { IntArray(n) }
        var count = 0
        for (i in 0 until n / 2) {
            val edge = n - 1 - 2 * i
            for (j in i until i + edge) {
                val num = count + j - i + 1
                matrix[i][j] = num
                matrix[j][n - 1 - i] = num + edge
                matrix[n - 1 - i][n - 1 - j] = num + edge * 2
                matrix[n - 1 - j][i] = num + edge * 3
            }
            count += edge * 4
        }
        if (n % 2 == 1) matrix[n / 2][n / 2] = count + 1
        return matrix
    }
}