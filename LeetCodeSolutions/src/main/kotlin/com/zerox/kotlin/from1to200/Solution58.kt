package com.zerox.kotlin.from1to200

/**
 * 58. 最后一个单词的长度 | 难度：简单 | 标签：字符串
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 *
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 *
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为6的“joyboy”。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 15:45
 */
object Solution58 {
    /**
     * 执行用时：204 ms, 在所有 Kotlin 提交中击败了 5.26% 的用户
     * 内存消耗：35.2 MB, 在所有 Kotlin 提交中击败了 23.68% 的用户
     * 通过测试用例：58 / 58
     */
    fun lengthOfLastWord_split(s: String): Int {
        // 傻逼 Kotlin 重写 split，坑人啊！
        return s.trim().split(Regex("\\s+")).last().length
    }

    /**
     * 执行用时：148 ms, 在所有 Kotlin 提交中击败了 86.84% 的用户
     * 内存消耗：33.9 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：58 / 58
     */
    fun lengthOfLastWord(s: String): Int {
        var i = s.length - 1
        while (s[i] == ' ') {
            i -= 1
        }
        var res = 0
        while (i >= 0 && s[i] != ' ') {
            res++
            i -= 1
        }
        return res
    }
}