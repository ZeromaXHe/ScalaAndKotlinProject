package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/8/3 17:03
 * @note
 * 6. Z 字形变换 | 难度：中等 | 标签：字符串
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution6 {
  /**
   * 执行用时：516 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：1157 / 1157
   *
   * @param s
   * @param numRows
   * @return
   */
  def convert(s: String, numRows: Int): String = {
    if (numRows == 1) return s
    val sb = StringBuilder.newBuilder
    var i = 0
    while (i < s.length) {
      sb.append(s(i))
      i += (numRows - 1) * 2
    }
    for (j <- 1 until (numRows - 1)) {
      i = j
      while (i < s.length) {
        sb.append(s(i))
        i += (numRows - 1 - j) * 2
        if (i < s.length) sb.append(s(i))
        i += j * 2
      }
    }
    i = numRows - 1
    while (i < s.length) {
      sb.append(s(i))
      i += (numRows - 1) * 2
    }
    sb.toString()
  }
}
