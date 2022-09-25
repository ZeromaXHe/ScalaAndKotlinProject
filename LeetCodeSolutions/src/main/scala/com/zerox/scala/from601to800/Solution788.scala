package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/9/25 10:04
 * @note
 * 788. 旋转数字 | 难度：中等 | 标签：数学、动态规划
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 *
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 *
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 *
 * 示例：
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 *
 * 提示：
 * N 的取值范围是 [1, 10000]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotated-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution788 {
  /**
   * 执行用时：436 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：50 / 50
   *
   * @param n
   * @return
   */
  def rotatedDigits(n: Int): Int = {
    var count = 0
    val rotateSet = Set('2', '5', '6', '9', '0', '1', '8')
    val rotateDiffSet = Set('2', '5', '6', '9')
    val multi = Array(1, 10, 100, 1000, 10000)
    var i = 1
    while (i <= n) {
      val str = i.toString
      val idx = str.indexWhere(!rotateSet(_))
      if (idx >= 0) {
        val mul = multi(str.length - 1 - idx)
        i = (i / mul + 1) * mul
      } else {
        if (str.exists(rotateDiffSet)) count += 1
        i += 1
      }
    }
    count
  }
}
