package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/8/28 9:03
 * @note
 * 793. 阶乘函数后 K 个零 | 难度：困难 | 标签：数学、二分查找
 * f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 *
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 *
 * 示例 1：
 * 输入：k = 0
 * 输出：5
 * 解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
 *
 * 示例 2：
 * 输入：k = 5
 * 输出：0
 * 解释：没有匹配到这样的 x!，符合 k = 5 的条件。
 *
 * 示例 3:
 * 输入: k = 3
 * 输出: 5
 *
 * 提示:
 * 0 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution793 {
  /**
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：44 / 44
   *
   * @param k
   * @return
   */
  def preimageSizeFZF(k: Int): Int = {
    (binarySearch(k + 1) - binarySearch(k)).toInt
  }

  private def binarySearch(k: Int): Long = {
    var r = 5L * k
    var l = 0L
    while (l <= r) {
      val mid = (l + r) / 2
      if (trailingZeroes(mid) < k) l = mid + 1
      else r = mid - 1
    }
    r + 1
  }

  private def trailingZeroes(n: Long): Long = {
    var res = 0L
    var vn = n
    while (vn > 0) {
      vn /= 5
      res += vn
    }
    res
  }
}
