package com.zerox.kotlin.from401to600

/**
 * 473. 火柴拼正方形 | 难度：中等 | 标签：位运算、数组、动态规划、回溯、状态压缩
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 *
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 *
 * 示例 1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 *
 * 示例 2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *
 * 提示:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/matchsticks-to-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 16:50
 */
object Solution473 {
    /**
     * 执行用时：324 ms, 在所有 Kotlin 提交中击败了 33.33% 的用户
     * 内存消耗：36 MB, 在所有 Kotlin 提交中击败了 13.89% 的用户
     * 通过测试用例：195 / 195
     */
    fun makesquare(matchsticks: IntArray): Boolean {
        val totalLen = matchsticks.sum()
        if (totalLen % 4 != 0) {
            return false
        }
        val len = totalLen / 4
        val n = matchsticks.size
        val dp = IntArray(1 shl n) { -1 }
        dp[0] = 0
        // 对所有的火柴组合方式进行遍历
        for (s in 1 until (1 shl n)) {
            for (k in 0 until n) {
                if ((s and (1 shl k)) == 0) {
                    // 第 k 根火柴不在组合中
                    continue
                }
                // 组合 s 去掉 k 后的组合为 s1
                val s1 = s and ((1 shl k).inv())
                if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                    dp[s] = (dp[s1] + matchsticks[k]) % len
                    break
                }
            }
        }
        return dp[(1 shl n) - 1] == 0
    }
}