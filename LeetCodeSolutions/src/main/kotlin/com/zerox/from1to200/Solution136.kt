package com.zerox.from1to200

/**
 * 136. 只出现一次的数字 | 难度：简单 | 标签：位运算、数组
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 11:51
 */
object Solution136 {
    /**
     * 执行用时：228 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 内存消耗：37.1 MB, 在所有 Kotlin 提交中击败了 60.53% 的用户
     * 通过测试用例：61 / 61
     */
    fun singleNumber(nums: IntArray): Int {
        // 力扣 1.3 版本不支持 scan
//        return nums.scan(0) {acc, num -> acc xor num}.last()
        var result = 0
        nums.forEach { result = result xor it }
        return result
    }
}