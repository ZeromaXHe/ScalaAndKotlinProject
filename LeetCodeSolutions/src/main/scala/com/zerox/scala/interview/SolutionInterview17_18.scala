package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/7/11 16:05
 * @note
 * 面试题 17.18. 最短超串 | 难度：中等 | 标签：数组、哈希表、滑动窗口
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 *
 * 示例 1:
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 *
 * 示例 2:
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 *
 * 提示：
 * big.length <= 100000
 * 1 <= small.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-supersequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_18 {
  /**
   * 执行用时：1060 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：79.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：42 / 42
   *
   * @param big
   * @param small
   * @return
   */
  def shortestSeq(big: Array[Int], small: Array[Int]): Array[Int] = {
    val set = Set[Int](small: _*)
    var from = 0
    var to = 0
    val tmp = new scala.collection.mutable.HashMap[Int, Int]
    while (to < big.length && tmp.size < set.size) {
      if (set(big(to))) tmp(big(to)) = tmp.getOrElse(big(to), 0) + 1
      to += 1
    }
    if (tmp.size < set.size) return Array.empty
    val res = Array(from, to - 1)
    while (from < to) {
      val i = big(from)
      if (set(i)) if (tmp(i) == 1) tmp remove i else tmp(i) = tmp(i) - 1
      from += 1
      if (tmp.size == set.size) {
        if (to - from < res(1) + 1 - res(0)) {
          res(0) = from
          res(1) = to - 1
        }
      } else {
        while (to < big.length && tmp.size < set.size) {
          if (set(big(to))) tmp(big(to)) = tmp.getOrElse(big(to), 0) + 1
          to += 1
        }
      }
    }
    if (tmp.size == set.size && to - from < res(1) + 1 - res(0)) {
      res(0) = from
      res(1) = to - 1
    }
    res
  }
}
