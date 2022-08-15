package com.zerox.kotlin.from1801to2000

/**
 * 1886. 判断矩阵经轮转后是否一致 | 难度：简单 | 标签：数组、矩阵
 * 给你两个大小为 n x n 的二进制矩阵 mat 和 target 。现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * 输出：true
 * 解释：顺时针轮转 90 度一次可以使 mat 和 target 一致。
 *
 * 示例 2：
 * 输入：mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
 * 输出：false
 * 解释：无法通过轮转矩阵中的元素使 equal 与 target 一致。
 *
 * 示例 3：
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
 * 输出：true
 * 解释：顺时针轮转 90 度两次可以使 mat 和 target 一致。
 *
 * 提示：
 * n == mat.length == target.length
 * n == mat[i].length == target[i].length
 * 1 <= n <= 10
 * mat[i][j] 和 target[i][j] 不是 0 就是 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 11:03
 */
object Solution1886 {
    /**
     * 执行用时：172 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：38.9 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：113 / 113
     */
    fun findRotation(mat: Array<IntArray>, target: Array<IntArray>): Boolean {
        val failRot = BooleanArray(4)
        var fail = 0
        val n = mat.size
        if (n % 2 == 1 && mat[n / 2][n / 2] != target[n / 2][n / 2]) return false
        for (i in 0 until n / 2) {
            for (j in 0 until (n + 1) / 2) {
                if (fail == 4) return false
                if (!failRot[0]) {
                    if (mat[i][j] != target[i][j]
                        || mat[n - 1 - j][i] != target[n - 1 - j][i]
                        || mat[n - 1 - i][n - 1 - j] != target[n - 1 - i][n - 1 - j]
                        || mat[j][n - 1 - i] != target[j][n - 1 - i]
                    ) {
                        failRot[0] = true
                        fail++
                    }
                }
                if (!failRot[1]) {
                    if (mat[i][j] != target[n - 1 - j][i]
                        || mat[n - 1 - j][i] != target[n - 1 - i][n - 1 - j]
                        || mat[n - 1 - i][n - 1 - j] != target[j][n - 1 - i]
                        || mat[j][n - 1 - i] != target[i][j]
                    ) {
                        failRot[1] = true
                        fail++
                    }
                }
                if (!failRot[2]) {
                    if (mat[i][j] != target[n - 1 - i][n - 1 - j]
                        || mat[n - 1 - j][i] != target[j][n - 1 - i]
                        || mat[n - 1 - i][n - 1 - j] != target[i][j]
                        || mat[j][n - 1 - i] != target[n - 1 - j][i]
                    ) {
                        failRot[2] = true
                        fail++
                    }
                }
                if (!failRot[3]) {
                    if (mat[i][j] != target[j][n - 1 - i]
                        || mat[n - 1 - j][i] != target[i][j]
                        || mat[n - 1 - i][n - 1 - j] != target[n - 1 - j][i]
                        || mat[j][n - 1 - i] != target[n - 1 - i][n - 1 - j]
                    ) {
                        failRot[3] = true
                        fail++
                    }
                }
            }
        }
        return fail < 4
    }
}