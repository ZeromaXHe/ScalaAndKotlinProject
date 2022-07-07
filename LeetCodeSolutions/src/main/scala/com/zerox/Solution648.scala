package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/7 9:39
 * @note
 * 648. 单词替换 | 难度：中等 | 标签：字典树、数组、哈希表、字符串
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 你需要输出替换之后的句子。
 *
 * 示例 1：
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 *
 * 示例 2：
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 *
 * 提示：
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/replace-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution648 {
  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：65 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：126 / 126
   *
   * @param dictionary
   * @param sentence
   * @return
   */
  def replaceWords(dictionary: List[String], sentence: String): String = {
    // 构建字典树
    val trie = new Trie('#', false)
    var pre = trie
    dictionary.foreach(str => {
      pre = trie
      str.foreach(ch => {
        if (pre.children(ch - 'a') == null) {
          pre.children(ch - 'a') = new Trie(ch, false)
        }
        pre = pre.children(ch - 'a')
      })
      pre.end = true
    })
    // 解析句子
    val strings = sentence.split(" ")
    val builder = new StringBuilder
    val prefixBuilder = new StringBuilder
    strings.foreach(str => {
      var i = 0
      var noPrefix = false
      var findPrefix = false
      pre = trie
      prefixBuilder.clear()
      while (!noPrefix && !findPrefix && i < str.length) {
        if (pre.children(str(i) - 'a') == null) {
          noPrefix = true
        } else {
          prefixBuilder.append(str(i))
          pre = pre.children(str(i) - 'a')
          if (pre.end) findPrefix = true
        }
        i += 1
      }
      if (builder.nonEmpty) builder.append(" ")
      if (findPrefix) builder.append(prefixBuilder.toString())
      else builder.append(str)
    })

    builder.toString()
  }

  class Trie(val char: Char, var end: Boolean) {
    val children = new Array[Trie](26)
  }
}
