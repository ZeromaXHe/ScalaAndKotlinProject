package com.zerox.from601to800

/**
 * 640. 求解方程 | 难度：中等 | 标签：数学、字符串、模拟
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 *
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 *
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 *
 * 示例 1：
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 *
 * 示例 2:
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 *
 * 示例 3:
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *
 * 提示:
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/solve-the-equation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 10:08
 */
object Solution640 {
    /**
     * 执行用时：192 ms, 在所有 Kotlin 提交中击败了 66.67% 的用户
     * 内存消耗：36.1 MB, 在所有 Kotlin 提交中击败了 33.33% 的用户
     * 通过测试用例：59 / 59
     */
    fun solveEquation(equation: String): String {
        val split = equation.split("=")
        val left = getXAndNum(split[0])
        val right = getXAndNum(split[1])
        val x = left.first - right.first
        val num = left.second - right.second

        return if (x == 0) {
            if (num == 0) "Infinite solutions"
            else "No solution"
        } else "x=${-num / x}"
    }

    private fun getXAndNum(s: String): Pair<Int, Int> {
        val split = s.replace("-", "+-").split("+")
        var xCount = 0
        var numCount = 0
        split.filterNot { "".equals(it) }.forEach {
            if (it.endsWith("x")) {
                xCount +=
                    if (it.length == 1) 1
                    else if (it.length == 2 && it[0] == '-') -1
                    else it.substring(0, it.length - 1).toInt()
            } else numCount += it.toInt()
        }
        return Pair(xCount, numCount)
    }
}