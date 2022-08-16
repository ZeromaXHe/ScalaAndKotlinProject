package com.zerox.kotlin.from1to200

/**
 * 54. 螺旋矩阵 | 难度：中等 | 标签：数组、矩阵、模拟
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 10:15
 */
object Solution54 {
    /**
     * 执行用时：140 ms, 在所有 Kotlin 提交中击败了 88.89% 的用户
     * 内存消耗：33 MB, 在所有 Kotlin 提交中击败了 64.81% 的用户
     * 通过测试用例：23 / 23
     */
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        var minI = 1
        var maxI = matrix.size - 1
        var minJ = 0
        var maxJ = matrix[0].size - 1
        var i = 0
        var j = -1
        val total = matrix.size * matrix[0].size
        while (result.size < total) {
            while (result.size < total && j < maxJ) {
                j++
                result.add(matrix[i][j])
            }
            maxJ--
            while (result.size < total && i < maxI) {
                i++
                result.add(matrix[i][j])
            }
            maxI--
            while (result.size < total && j > minJ) {
                j--
                result.add(matrix[i][j])
            }
            minJ++
            while (result.size < total && i > minI) {
                i--
                result.add(matrix[i][j])
            }
            minI++
        }
        return result
    }
}