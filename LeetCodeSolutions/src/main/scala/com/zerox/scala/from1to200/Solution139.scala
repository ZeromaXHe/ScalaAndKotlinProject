package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/8/23 14:16
 * @note
 * 139. 单词拆分 | 难度：中等 | 标签：字典树、记忆化搜索、哈希表、字符串、动态规划
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution139 {
  /**
   * 执行用时：556 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：53 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：45 / 45
   *
   * @param s
   * @param wordDict
   * @return
   */
  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val dict = new Trie('#', false)
    dict.buildDict(wordDict)
    val dp = new Array[Boolean](s.length + 1)
    dp(0) = true
    for (i <- 1 to s.length) {
      var break = false
      for (j <- 0 until i if !break) {
        if (dp(j) && dict.contains(s, j, i)) {
          dp(i) = true
          break = true
        }
      }
    }

    dp(s.length)
  }

  class Trie(val char: Char, var end: Boolean) {
    val children = new Array[Trie](26)

    def buildDict(dictionary: List[String]): Unit = {
      var pre = this
      dictionary.foreach(str => {
        pre = this
        str.foreach(ch => {
          if (pre.children(ch - 'a') == null) pre.children(ch - 'a') = new Trie(ch, false)
          pre = pre.children(ch - 'a')
        })
        pre.end = true
      })
    }

    def contains(s: String, from: Int, to: Int): Boolean = {
      var node = this
      for (i <- from until to) {
        if (node.children(s(i) - 'a') == null) return false;
        node = node.children(s(i) - 'a')
      }
      node.end
    }
  }
}
