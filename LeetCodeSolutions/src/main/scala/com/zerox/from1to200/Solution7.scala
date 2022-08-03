package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/8/3 17:27
 * @note
 * 7. 整数反转 | 难度：中等 | 标签：数学
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 * -231 <= x <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution7 {
  def main(args: Array[String]): Unit = {
    println(-123 % 10)
  }

  /**
   * 执行用时：440 ms, 在所有 Scala 提交中击败了 54.55% 的用户
   * 内存消耗：51.4 MB, 在所有 Scala 提交中击败了 59.09% 的用户
   * 通过测试用例：1032 / 1032
   *
   * @param x
   * @return
   */
  def reverse(x: Int): Int = {
    val sign = if (x >= 0) 1 else -1
    var result = 0
    var vx = if (sign == 1) -x else x
    while (vx < 0) {
      if (result < Int.MinValue / 10) return 0
      result *= 10
      result += vx % 10
      vx /= 10
    }
    if (result > 0 || (sign == 1 && result == Int.MinValue)) 0
    else result * -sign
  }
}
