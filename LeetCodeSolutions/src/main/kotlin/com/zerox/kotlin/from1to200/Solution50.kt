package com.zerox.kotlin.from1to200

/**
 * 50. Pow(x, n) | 难度：中等 | 标签：递归、数学
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 18:14
 */
object Solution50 {
    /**
     * 执行用时：160 ms, 在所有 Kotlin 提交中击败了 95.56% 的用户
     * 内存消耗：36.1 MB, 在所有 Kotlin 提交中击败了 42.22% 的用户
     * 通过测试用例：305 / 305
     */
    fun myPow(x: Double, n: Int): Double {
        if (n < 0) return 1 / myPow(x, -(n.toLong()))
        return myPow(x, n.toLong())
    }

    private fun myPow(x: Double, n: Long): Double {
        if (n < 0) return 1 / myPow(x, -n)
        var vn = n
        var result = 1.0
        var prod = x
        while (vn > 0) {
            if (vn and 1L > 0) result *= prod
            prod *= prod
            vn /= 2
        }
        return result
    }
}