package com.zerox.kotlin.from1to200

/**
 * @author zhuxi
 * @since 2022/8/8 19:12
 * 14. 最长公共前缀 | 难度：简单 | 标签：字符串
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution14 {
    /**
     * 执行用时：212 ms, 在所有 Kotlin 提交中击败了 12.66% 的用户
     * 内存消耗：35.4 MB, 在所有 Kotlin 提交中击败了 35.44% 的用户
     * 通过测试用例：124 / 124
     */
    fun longestCommonPrefix_slow(strs: Array<String>): String {
        // 力扣上用的是 min()，不然报错。看国际站上的文档 Kotlin 版本是 1.3，估计是老的没有 minOrNull 方法
//        val min = strs.map { it.length }.min() ?: 0
        val min = strs.map { it.length }.minOrNull() ?: 0
        var i = 0
        while (i < min) {
            if (strs.any { it[i] != strs[0][i] }) break
            i += 1
        }
        return strs[0].substring(0, i)
    }

    /**
     * 执行用时：184 ms, 在所有 Kotlin 提交中击败了 55.06% 的用户
     * 内存消耗：34.4 MB, 在所有 Kotlin 提交中击败了 92.41% 的用户
     * 通过测试用例：124 / 124
     */
    fun longestCommonPrefix(strs: Array<String>): String {
        var i = 0
        while (i <= 200) {
            if (strs.any { i == it.length || it[i] != strs[0][i] }) break
            i += 1
        }
        return strs[0].substring(0, i)
    }
}