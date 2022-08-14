package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/23 10:39
 * @note
 * 2306. 公司命名 | 难度：困难 | 标签：位运算、数组、哈希表、字符串、枚举
 * 给你一个字符串数组 ideas 表示在公司命名过程中使用的名字列表。公司命名流程如下：
 *
 * 从 ideas 中选择 2 个 不同 名字，称为 ideaA 和 ideaB 。
 * 交换 ideaA 和 ideaB 的首字母。
 * 如果得到的两个新名字 都 不在 ideas 中，那么 ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
 * 否则，不是一个有效的名字。
 * 返回 不同 且有效的公司名字的数目。
 *
 * 示例 1：
 * 输入：ideas = ["coffee","donuts","time","toffee"]
 * 输出：6
 * 解释：下面列出一些有效的选择方案：
 * - ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
 * - ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
 * - ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
 * - ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
 * - ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
 * - ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
 * 因此，总共有 6 个不同的公司名字。
 *
 * 下面列出一些无效的选择方案：
 * - ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
 * - ("time", "toffee")：在原数组中存在交换后形成的两个名字。
 * - ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
 *
 * 示例 2：
 * 输入：ideas = ["lack","back"]
 * 输出：0
 * 解释：不存在有效的选择方案。因此，返回 0 。
 *
 * 提示：
 * 2 <= ideas.length <= 5 * 104
 * 1 <= ideas[i].length <= 10
 * ideas[i] 由小写英文字母组成
 * ideas 中的所有字符串 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/naming-a-company
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2306 {
  var hashIndex = 0

  def distinctNames2(ideas: Array[String]): Long = {
    val sets = ideas.groupBy(_.substring(1)).values.map(_.map(_ (0)).toSet).toArray

    (for (i <- sets.indices; j <- 0 until i)
      yield (sets(i) diff sets(j)).size.toLong * (sets(j) diff sets(i)).size).sum * 2
  }

  /**
   * 执行用时：1024 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：72.6 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：89 / 89
   *
   * @param ideas
   * @return
   */
  def distinctNames(ideas: Array[String]): Long = {
    hashIndex = 0
    val hash = new scala.collection.mutable.HashMap[String, Int]
    val bitsets = ideas.groupBy(_ (0)).values.map(v => toBitSet(hash, v.map(_.substring(1)))).toArray

    (for (i <- bitsets.indices; j <- 0 until i)
      yield (bitsets(i) &~ bitsets(j)).size.toLong * (bitsets(j) &~ bitsets(i)).size)
      .sum * 2
  }

  private def toBitSet(hash: scala.collection.mutable.HashMap[String, Int],
                       strs: Array[String]): scala.collection.mutable.BitSet = {
    val result = new scala.collection.mutable.BitSet
    for (s <- strs) {
      val digit = if (hash.contains(s)) hash(s) else {
        hash(s) = hashIndex
        hashIndex += 1
        hashIndex - 1
      }
      result += digit
    }
    result
  }

  def main(args: Array[String]): Unit = {
    println(distinctNames(Array("lack", "back")) /*== 0*/)
    println(distinctNames(Array("coffee", "donuts", "time", "toffee")) /*== 6*/)
    println(distinctNames(Array("coffee", "donuts", "time", "toffee", "koffee", "dime", "conuts")) /*== 10*/)
  }
}
