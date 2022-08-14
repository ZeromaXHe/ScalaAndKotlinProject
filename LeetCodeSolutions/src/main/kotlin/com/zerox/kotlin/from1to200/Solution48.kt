package com.zerox.kotlin.from1to200

/**
 * 48. 旋转图像 | 难度：中等 | 标签：数组、数学、矩阵
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 14:26
 */
object Solution48 {
    /**
     * 执行用时：172 ms, 在所有 Kotlin 提交中击败了 65.52% 的用户
     * 内存消耗：34.7 MB, 在所有 Kotlin 提交中击败了 41.38% 的用户
     * 通过测试用例：21 / 21
     */
    fun rotate(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size
        for (i in 0 until m / 2) {
            for (j in 0 until (n + 1) / 2) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[m - 1 - j][i]
                matrix[m - 1 - j][i] = matrix[m - 1 - i][n - 1 - j]
                matrix[m - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]
                matrix[j][n - 1 - i] = temp
            }
        }
    }
}