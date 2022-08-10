package com.zerox.from1to200

/**
 * 70. 爬楼梯 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 * 1 <= n <= 45
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 17:40
 */
object Solution70 {
    /**
     * 执行用时：116 ms, 在所有 Kotlin 提交中击败了 90.24% 的用户
     * 内存消耗：31.9 MB, 在所有 Kotlin 提交中击败了 69.92% 的用户
     * 通过测试用例：45 / 45
     *
     * 使用 % 和 sum 的话，耗时较长：
     * 执行用时：144 ms, 在所有 Kotlin 提交中击败了 17.07% 的用户
     * 内存消耗：34.4 MB, 在所有 Kotlin 提交中击败了 5.69% 的用户
     * 通过测试用例：45 / 45
     */
    fun climbStairs(n: Int): Int {
        if (n == 1) return 1
        val fib = intArrayOf(1, 1)
        for (i in 2..n) {
//            fib[i % 2] = fib.sum()
            fib[i and 1] = fib[0] + fib[1]
        }
//        return fib[n % 2]
        return fib[n and 1]
    }
}