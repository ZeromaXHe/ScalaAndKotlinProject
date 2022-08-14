package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/6/27 9:45
 * @note
 * 522. 最长特殊序列 II | 难度：中等 | 标签：数组、哈希表、双指针、字符串、排序
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 *
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 *
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 *
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 *
 * 示例 1：
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 *
 * 示例 2:
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 *
 * 提示:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution522 {
  def main(args: Array[String]): Unit = {
    println(findLUSlength(Array("aba", "cdc", "eae")))
    println(findLUSlength(Array("aaa", "aaa", "aa")))
  }

  /**
   * 执行用时：524 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：98 / 98
   *
   * @param strs
   * @return
   */
  def findLUSlength(strs: Array[String]): Int = {
    var max = -1
    for (i <- strs.indices) {
      var check = true
      var j = 0
      while (j < strs.length && check) {
        if (i != j && subSeq(strs(i), strs(j))) check = false
        j += 1
      }
      if (check) max = Math.max(max, strs(i).length)
    }
    max
  }

  /**
   * 使用 subSeq 方法
   * 执行用时：484 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：98 / 98
   *
   * 使用 subSeqByIndexOf 方法
   * 执行用时：524 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：98 / 98
   *
   * @param strs
   * @return
   */
  def findLUSlength_zipWithIndex(strs: Array[String]): Int = {
    val maxs = for (i <- strs.indices
                    if strs.zipWithIndex.filter(i != _._2).forall(zip => !subSeq(strs(i), zip._1))
                    ) yield strs(i).length
    if (maxs.isEmpty) -1
    else maxs.max
  }

  private def subSeq(str1: String, str2: String): Boolean = {
    // Scala 加这个反而耗时变成 520ms 了…… 在 Java 里面这样判断一下反而减时间
    // if (str1.length > str2.length) {
    //   return false
    // }
    var p1 = 0
    var p2 = 0
    while (p1 < str1.length && p2 < str2.length) {
      if (str1.charAt(p1) == str2.charAt(p2)) {
        p1 += 1
      }
      p2 += 1
    }
    p1 == str1.length
  }

  private def subSeqByIndexOf(str1: String, str2: String): Boolean = {
    // 这里去掉的话就是 540ms …… 感觉就很迷，波动有点大。
    if (str1.length > str2.length) {
      return false
    }
    var index = -1
    str1.forall(c => {
      index = str2.indexOf(c, index + 1)
      index != -1
    })
  }
}
