package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/6/30 18:24
 * @note
 * 面试题 16.20. T9键盘 | 难度：中等 | 标签：数组、哈希表、字符串
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
 *
 * 示例 1:
 * 输入: num = "8733", words = ["tree", "used"]
 * 输出: ["tree", "used"]
 *
 * 示例 2:
 * 输入: num = "2", words = ["a", "b", "c", "d"]
 * 输出: ["a", "b", "c"]
 *
 * 提示：
 * num.length <= 1000
 * words.length <= 500
 * words[i].length == num.length
 * num中不会出现 0, 1 这两个数字
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/t9-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_20 {
  val map = Map(
    '2' -> Set('a', 'b', 'c'), '3' -> Set('d', 'e', 'f'),
    '4' -> Set('g', 'h', 'i'), '5' -> Set('j', 'k', 'l'), '6' -> Set('m', 'n', 'o'),
    '7' -> Set('p', 'q', 'r', 's'), '8' -> Set('t', 'u', 'v'), '9' -> Set('w', 'x', 'y', 'z')
  )

  /**
   * 执行用时：568 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：33 / 33
   *
   * @param num
   * @param words
   * @return
   */
  def getValidT9Words(num: String, words: Array[String]): List[String] = {
    words.filter(word => {
      var filt = true
      var i = 0
      while (filt && i < word.length) {
        if (!map(num(i))(word(i))) filt = false
        i += 1
      }
      filt
    }).toList
  }
}
