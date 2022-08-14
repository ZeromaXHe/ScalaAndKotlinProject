package com.zerox.kotlin.from1to200

/**
 * 74. 搜索二维矩阵 | 难度：中等 | 标签：数组、二分查找、矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhuxi
 * @since 2022/8/9 11:44
 */
object Solution74 {
    /**
     * 执行用时：172 ms, 在所有 Kotlin 提交中击败了 60.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Kotlin 提交中击败了 80.00% 的用户
     * 通过测试用例：133 / 133
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var i = matrix.size - 1
        var j = 0
        while (j < matrix[0].size && i >= 0) {
            if (matrix[i][j] == target) return true
            if (matrix[i][j] < target) j++
            else i--
        }
        return false
    }
}