package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 16:52
 * @note
 * 246. 中心对称数 | 难度：简单 | 标签：哈希表、双指针、字符串
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 * 示例 1:
 * 输入: num = "69"
 * 输出: true
 *
 * 示例 2:
 * 输入: num = "88"
 * 输出: true
 *
 * 示例 3:
 * 输入: num = "962"
 * 输出: false
 *
 * 示例 4：
 * 输入：num = "1"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strobogrammatic-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution246 {
  val map = Map('6' -> '9', '8' -> '8', '9' -> '6', '1' -> '1', '0' -> '0')

  /**
   * 执行用时：424 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：49 / 49
   *
   * @param num
   * @return
   */
  def isStrobogrammatic(num: String): Boolean = {
    for (i <- 0 until num.length / 2) {
      if (!map.contains(num(i)) || num(num.length - 1 - i) != map(num(i))) return false
    }
    if (num.length % 2 == 1) {
      val mid = num(num.length / 2)
      map.contains(mid) && map(mid) == mid
    } else true
  }
}
