package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/8/26 13:52
 * @note
 * 451. 根据字符出现频率排序 | 难度：中等 | 标签：哈希表、字符串、桶排序、计数、排序、堆（优先队列）
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 *
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 *
 * 示例 1:
 * 输入: s = "tree"
 * 输出: "eert"
 * 解释: 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *
 * 示例 2:
 * 输入: s = "cccaaa"
 * 输出: "cccaaa"
 * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *
 * 示例 3:
 * 输入: s = "Aabb"
 * 输出: "bbAa"
 * 解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * 提示:
 * 1 <= s.length <= 5 * 105
 * s 由大小写英文字母和数字组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution451 {
  /**
   * 执行用时：600 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.9 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：33 / 33
   *
   * @param s
   * @return
   */
  def frequencySort(s: String): String = {
    val map = new scala.collection.mutable.HashMap[Char, Int]
    for (c <- s) {
      map.put(c, map.getOrElse(c, 0) + 1)
    }
    // 大顶堆
    val queue = new scala.collection.mutable.PriorityQueue[(Int, Char)]
    for ((ch, count) <- map) {
      queue.enqueue((count, ch))
    }
    val sb = new StringBuilder
    while (queue.nonEmpty) {
      val deq = queue.dequeue()
      sb.append(deq._2.toString * deq._1)
    }
    sb.toString()
  }
}
