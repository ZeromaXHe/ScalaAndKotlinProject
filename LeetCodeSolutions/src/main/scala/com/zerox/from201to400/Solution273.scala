package com.zerox.from201to400

import com.zerox.interview.SolutionInterview16_08

/**
 * @author zhuxi
 * @since 2022/6/30 14:48
 * @note
 * 273. 整数转换英文表示 | 难度：困难 | 标签：递归、数学、字符串
 * 将非负整数 num 转换为其对应的英文表示。
 *
 * 示例 1：
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 *
 * 示例 2：
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 *
 * 示例 3：
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * 提示：
 * 0 <= num <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution273 {
  /**
   * 执行用时：452 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：601 / 601
   *
   * 提交的是直接复制“面试题 16，08”的代码
   *
   * @param num
   * @return
   */
  def numberToWords(num: Int): String = {
    SolutionInterview16_08.numberToWords(num)
  }
}
