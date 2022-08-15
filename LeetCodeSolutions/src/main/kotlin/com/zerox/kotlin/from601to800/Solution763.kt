package com.zerox.kotlin.from601to800

/**
 * 763. 划分字母区间 | 难度：中等 | 标签：贪心、哈希表、双指针、字符串
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 14:57
 */
object Solution763 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(partitionLabels("ababcbacadefegdehijhklij"))
    }

    /**
     * 执行用时：164 ms, 在所有 Kotlin 提交中击败了 85.71% 的用户
     * 内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了 14.29% 的用户
     * 通过测试用例：118 / 118
     */
    fun partitionLabels(s: String): List<Int> {
        /**
         * 使用注释的代码生成 map 和使用 map 的话：
         * 执行用时：220 ms, 在所有 Kotlin 提交中击败了 14.29% 的用户
         * 内存消耗：36.3 MB, 在所有 Kotlin 提交中击败了 14.29% 的用户
         * 通过测试用例：118 / 118
         */
        // 力扣 Kotlin 1.3 没有 maxOrNull，得改成 max
//        val map = s.indices.groupBy { s[it] }.mapValues { it.value.maxOrNull() }
        val map = IntArray(26)
        for (i in s.indices) {
            map[s[i] - 'a'] = i
        }
        var from = 0
        var to = 0
        val list = mutableListOf<Int>()
        var i = 0
        while (to < s.length - 1) {
//            to = to.coerceAtLeast(map[s[i]]!!)
            to = to.coerceAtLeast(map[s[i] - 'a'])
            if (i == to) {
                list.add(to - from + 1)
                from = i + 1
            }
            i++
        }
        if (from < s.length) list.add(s.length - from)
        return list
    }
}