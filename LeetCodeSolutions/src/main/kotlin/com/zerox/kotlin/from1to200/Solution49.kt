package com.zerox.kotlin.from1to200

/**
 * 49. 字母异位词分组 | 难度：中等 | 标签：
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 11:28
 */
object Solution49 {
    /**
     * 执行用时：336 ms, 在所有 Kotlin 提交中击败了 49.09% 的用户
     * 内存消耗：40.4 MB, 在所有 Kotlin 提交中击败了 78.18% 的用户
     * 通过测试用例：117 / 117
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs.groupBy { strToCharCountStr(it) }.values.toList()
    }

    private fun strToCharCountStr(str: String): String {
        val arr = IntArray(26)
        str.forEach { arr[it - 'a']++ }
        val sb = StringBuilder()
        arr.forEach { sb.append(it).append('|') }
        return sb.toString()
    }
}