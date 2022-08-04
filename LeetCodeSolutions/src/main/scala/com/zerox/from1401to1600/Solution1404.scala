package com.zerox.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/4 14:02
 * @note
 * 1404. 将二进制表示减到 1 的步骤数 | 难度：中等 | 标签：位运算、字符串
 * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
 *
 * 如果当前数字为偶数，则将其除以 2 。
 *
 * 如果当前数字为奇数，则将其加上 1 。
 *
 * 题目保证你总是可以按上述规则将测试用例变为 1 。
 *
 * 示例 1：
 * 输入：s = "1101"
 * 输出：6
 * 解释："1101" 表示十进制数 13 。
 * Step 1) 13 是奇数，加 1 得到 14 
 * Step 2) 14 是偶数，除 2 得到 7
 * Step 3) 7  是奇数，加 1 得到 8
 * Step 4) 8  是偶数，除 2 得到 4 
 * Step 5) 4  是偶数，除 2 得到 2 
 * Step 6) 2  是偶数，除 2 得到 1 
 *
 * 示例 2：
 * 输入：s = "10"
 * 输出：1
 * 解释："10" 表示十进制数 2 。
 * Step 1) 2 是偶数，除 2 得到 1
 *
 * 示例 3：
 * 输入：s = "1"
 * 输出：0
 *
 * 提示：
 * 1 <= s.length <= 500
 * s 由字符 '0' 或 '1' 组成。
 * s[0] == '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1404 {
  /**
   * 执行用时：480 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param s
   * @return
   */
  def numSteps(s: String): Int = {
    var count = 0
    var i = s.length - 1
    val sb = new StringBuilder(s)
    while (i >= 0) {
      while (i >= 0 && sb(i) == '0') {
        count += 1
        i -= 1
      }
      if (i > 0) {
        count += 1
        while (i >= 0 && sb(i) == '1') {
          count += 1
          i -= 1
        }
        if (i >= 0) sb(i) = '1'
        else sb.insert(0, '1')
      } else {
        i -= 1
      }
    }
    count
  }
}
