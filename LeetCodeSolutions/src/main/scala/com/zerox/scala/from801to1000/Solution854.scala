package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/9/21 9:53
 * @note
 * 854. 相似度为 K 的字符串 | 难度：困难 | 标签：广度优先搜索、字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 *
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 *
 * 示例 1：
 * 输入：s1 = "ab", s2 = "ba"
 * 输出：1
 *
 * 示例 2：
 * 输入：s1 = "abc", s2 = "bca"
 * 输出：2
 *
 * 提示：
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-similar-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution854 {
  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：62 / 62
   *
   * 参考题解做的
   *
   * @param s1
   * @param s2
   * @return
   */
  def kSimilarity(s1: String, s2: String): Int = {
    val n = s1.length
    import scala.collection.mutable
    val queue = new mutable.Queue[(String, Int)]
    val visit = new mutable.HashSet[String]
    queue.enqueue((s1, 0))
    visit += s1
    var step = 0
    while (queue.nonEmpty) {
      val size = queue.size
      for (i <- 0 until size) {
        var (cur, pos) = queue.dequeue()
        if (cur.equals(s2)) return step
        while (pos < n && cur(pos) == s2(pos)) pos += 1
        for (j <- pos + 1 until n if s2(j) != cur(j) && s2(pos) == cur(j)) {
          val arr = cur.toCharArray
          val c = arr(pos)
          arr(pos) = arr(j)
          arr(j) = c
          val next = new String(arr)
          if (!visit(next)) {
            visit.add(next)
            queue.enqueue((next, pos + 1))
          }
        }
      }
      step += 1
    }
    step
  }
}
