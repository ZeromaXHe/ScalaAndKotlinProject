package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/9 18:16
 * @note
 * 剑指 Offer 45. 把数组排成最小的数 | 难度：中等 | 标签：贪心、字符串、排序
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 *
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer45 {
  def main(args: Array[String]): Unit = {
    println(minNumber(Array(12, 121)))
  }

  /**
   * 执行用时：508 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53 MB, 在所有 Scala 提交中击败了 66.67% 的用户
   * 通过测试用例：222 / 222
   *
   * @param nums
   * @return
   */
  def minNumber(nums: Array[Int]): String = {
    val builder = new StringBuilder
    nums.map(_.toString).sorted(new Ordering[String] {
      override def compare(x: String, y: String): Int = {
        if (x == y) return 0
        var i = 0
        while (i < x.length + y.length) {
          val c1 = if (i < x.length) x(i) else y(i - x.length)
          val c2 = if (i < y.length) y(i) else x(i - y.length)
          if (c1 != c2) return c1 - c2
          i += 1
        }
        0
      }
    }).foreach(builder.append)
    builder.toString()
  }

  /**
   * 执行用时：492 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.9 MB, 在所有 Scala 提交中击败了 66.67% 的用户
   * 通过测试用例：222 / 222
   *
   * @param nums
   * @return
   */
  def minNumber_sortWith(nums: Array[Int]): String = {
    val builder = new StringBuilder
    nums.map(_.toString).sortWith((x, y) => {
      var result = true
      if (x != y) {
        var i = 0
        var continue = true
        while (continue && i < x.length + y.length) {
          val c1 = if (i < x.length) x(i) else y(i - x.length)
          val c2 = if (i < y.length) y(i) else x(i - y.length)
          if (c1 != c2) {
            result = c1 - c2 < 0
            continue = false
          }
          i += 1
        }
      }
      result
    }).foreach(builder.append)
    builder.toString()
  }

  /**
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.2 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：222 / 222
   *
   * @param nums
   * @return
   */
  def minNumber_sortWith_plus(nums: Array[Int]): String = {
    val builder = new StringBuilder
    nums.map(_.toString).sortWith((x, y) => x + y < y + x).foreach(builder.append)
    builder.toString()
  }
}
