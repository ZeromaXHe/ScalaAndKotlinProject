package com.zerox.from1001to1200

/**
 * @author zhuxi
 * @since 2022/8/9 10:04
 * 1137. 第 N 个泰波那契数 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 泰波那契序列 Tn 定义如下： 
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 *
 * 提示：
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-th-tribonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1137 {
    /**
     * 执行用时：136 ms, 在所有 Kotlin 提交中击败了 12.50% 的用户
     * 内存消耗：34.3 MB, 在所有 Kotlin 提交中击败了 12.50% 的用户
     * 通过测试用例：39 / 39
     */
    fun tribonacci(n: Int): Int {
        val dp = arrayOf(0, 1, 1)
        if (n <= 2) return dp[n]
        for (i in 3..n) {
            dp[i % 3] = dp.sum()
        }
        return dp[n % 3]
    }
}