package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 14:29
 * @note
 * 面试题 16.08. 整数的英语表示 | 难度：困难 | 标签：递归、数学、字符串
 * 给定一个整数，打印该整数的英文描述。
 *
 * 示例 1:
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 *
 * 示例 2:
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 *
 * 示例 3:
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * 示例 4:
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * 注意：本题与 273 题相同：https://leetcode-cn.com/problems/integer-to-english-words/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/english-int-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_08 {
  private val ENGLISH =
    Array[String]("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
      "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty")
  private val ENGLISH10 =
    Array[String]("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
  private val AFFIX = Array[String]("Billion", "Million", "Thousand")

  /**
   * 执行用时：480 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：601 / 601
   *
   * @param num
   * @return
   */
  def numberToWords(num: Int): String = {
    if (num == 0) return "Zero"
    val threeDigits = new Array[Int](4)
    threeDigits(0) = num / 1000000000
    threeDigits(1) = (num / 1000000) % 1000
    threeDigits(2) = (num / 1000) % 1000
    threeDigits(3) = num % 1000
    val sb = new StringBuilder
    for (i <- threeDigits.indices if threeDigits(i) != 0) {
      transformThreeDigitToEnglish(sb, threeDigits(i))
      if (i < 3) sb.append(' ').append(AFFIX(i))
    }
    sb.toString()
  }

  private def transformThreeDigitToEnglish(sb: StringBuilder, threeDigit: Int): Unit = {
    val digit0 = threeDigit / 100
    val digit1 = threeDigit / 10 % 10
    val digit2 = threeDigit % 10
    if (sb.length > 0) sb.append(' ')
    if (digit0 != 0) sb.append(ENGLISH(digit0)).append(' ').append("Hundred")
    if (digit1 <= 1) {
      if (digit2 == digit1 && digit1 == 0) return
      if (digit0 != 0) sb.append(' ')
      sb.append(ENGLISH(threeDigit % 100))
      return
    } else {
      if (digit0 != 0) sb.append(' ')
      sb.append(ENGLISH10(digit1))
    }
    if (digit2 != 0) sb.append(' ').append(ENGLISH(digit2))
  }
}
