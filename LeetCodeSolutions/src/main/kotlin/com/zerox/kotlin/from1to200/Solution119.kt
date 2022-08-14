package com.zerox.kotlin.from1to200

/**
 * 119. 杨辉三角 II | 难度：简单 | 标签：数组、动态规划
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 *
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 *
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *
 * 提示:
 * 0 <= rowIndex <= 33
 *
 * 进阶：
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 14:02
 */
object Solution119 {
    @JvmStatic
    fun main(args: Array<String>) {
        (10 downTo 1 step 2).forEach { println(it) }
    }

    /**
     * 执行用时：136 ms, 在所有 Kotlin 提交中击败了 89.66% 的用户
     * 内存消耗：32.4 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：34 / 34
     */
    fun getRow(rowIndex: Int): List<Int> {
        val list = ArrayList<Int>(rowIndex + 1)
        list.add(1)
        while (list.size <= rowIndex) {
            for (i in list.size - 1 downTo 1) {
                list[i] += list[i - 1]
            }
            list.add(1)
        }
        return list
    }
}