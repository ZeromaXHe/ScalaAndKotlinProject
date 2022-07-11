package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/9 17:54
 * @note
 * 剑指 Offer 46. 把数字翻译成字符串 | 难度：中等 | 标签：字符串、动态规划
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 231
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer46 {
  def main(args: Array[String]): Unit = {
    println(translateNum(25) == 2)
  }

  /**
   * 执行用时：400 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：49 / 49
   *
   * @param num
   * @return
   */
  def translateNum(num: Int): Int = {
    val str = num.toString
    var dp0 = 1
    var dp1 = 1
    for (i <- 1 until str.length) {
      val dpNew =
        if (str(i - 1) == '1') dp0 + dp1
        else if (str(i - 1) == '2') {
          if (str(i) < '6') dp0 + dp1
          else dp1
        } else dp1
      dp0 = dp1
      dp1 = dpNew
    }
    dp1
  }
}
