package com.zerox.from1to200

/**
 * 135. 分发糖果 | 难度：困难 | 标签：贪心、数组
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 示例 1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 * 提示：
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 18:29
 */
object Solution135 {
    /**
     * 执行用时：212 ms, 在所有 Kotlin 提交中击败了 90.91% 的用户
     * 内存消耗：37.4 MB, 在所有 Kotlin 提交中击败了 90.91% 的用户
     * 通过测试用例：48 / 48
     */
    fun candy(ratings: IntArray): Int {
        var result = 1
        var incr = 1
        var decr = 0
        var pre = 1
        for (i in 1 until ratings.size) {
            if (ratings[i] >= ratings[i - 1]) {
                pre = if (ratings[i] == ratings[i - 1]) 1 else (pre + 1)
                result += pre
                incr = pre
                decr = 0
            } else {
                decr++
                if (decr == incr) decr++
                result += decr
                pre = 1
            }
        }
        return result
    }
}