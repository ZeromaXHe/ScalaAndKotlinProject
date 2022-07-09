package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/9 19:17
 * @note
 * 剑指 Offer 44. 数字序列中某一位的数字 | 难度：中等 | 标签：数学、二分查找
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 *
 * 限制：
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer44 {
  def main(args: Array[String]): Unit = {
    println(findNthDigit(3))
    println(findNthDigit(30))
  }

  /**
   * 执行用时：404 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：70 / 70
   *
   * @param n
   * @return
   */
  def findNthDigit(n: Int): Int = {
    var limit = 10L
    var digit = 1
    var count = 9L
    var pre = 1L
    while (limit < n) {
      pre = limit
      digit += 1
      count *= 10
      limit += digit * count
    }
    val str = ((n - pre) / digit + count / 9).toString
    str(((n - pre) % digit).toInt) - '0'
  }
}
