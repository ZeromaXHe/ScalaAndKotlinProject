package com.zerox.from1401to1600

/**
 * 1545. 找出第 N 个二进制字符串中的第 K 位 | 难度：中等 | 标签：递归、字符串
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 *
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
 *
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 *
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出："0"
 * 解释：S3 为 "0111001"，其第 1 位为 "0" 。
 *
 * 示例 2：
 * 输入：n = 4, k = 11
 * 输出："1"
 * 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
 *
 * 示例 3：
 * 输入：n = 1, k = 1
 * 输出："0"
 *
 * 示例 4：
 * 输入：n = 2, k = 3
 * 输出："1"
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 18:07
 */
object Solution1545 {
    /**
     * 执行用时：140 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：32.8 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：63 / 63
     */
    fun findKthBit_notUsingN(n: Int, k: Int): Char {
        if (k == 1) return '0'
        var divide = 2
        while (divide < k) {
            divide *= 2
        }

        return (
                if (divide == k) '1'
                else if (findKthBit(n, divide - k) == '1') '0'
                else '1')
    }

    /**
     * 执行用时：180 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：32.8 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：63 / 63
     */
    fun findKthBit(n: Int, k: Int): Char {
        if (k == 1) return '0'
        val mid = 1 shl (n - 1)
        return (
                if (mid == k) '1'
                else if (mid > k) findKthBit(n - 1, k)
                else if (findKthBit(n - 1, mid * 2 - k) == '1') '0'
                else '1')
    }
}