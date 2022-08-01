package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/8/1 11:12
 * @note
 * 2310. 个位数字为 K 的整数之和 | 难度：中等 | 标签：贪心、数学、动态规划、枚举
 * 给你两个整数 num 和 k ，考虑具有以下属性的正整数多重集：
 *
 * 每个整数个位数字都是 k 。
 * 所有整数之和是 num 。
 * 返回该多重集的最小大小，如果不存在这样的多重集，返回 -1 。
 *
 * 注意：
 * 多重集与集合类似，但多重集可以包含多个同一整数，空多重集的和为 0 。
 * 个位数字 是数字最右边的数位。
 *
 * 示例 1：
 * 输入：num = 58, k = 9
 * 输出：2
 * 解释：
 * 多重集 [9,49] 满足题目条件，和为 58 且每个整数的个位数字是 9 。
 * 另一个满足条件的多重集是 [19,39] 。
 * 可以证明 2 是满足题目条件的多重集的最小长度。
 *
 * 示例 2：
 * 输入：num = 37, k = 2
 * 输出：-1
 * 解释：个位数字为 2 的整数无法相加得到 37 。
 *
 * 示例 3：
 * 输入：num = 0, k = 7
 * 输出：0
 * 解释：空多重集的和为 0 。
 *
 * 提示：
 * 0 <= num <= 3000
 * 0 <= k <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2310 {
  val map = Map(0 -> Set(0), 1 -> Set(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 2 -> Set(0, 2, 4, 6, 8),
    3 -> Set(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 4 -> Set(0, 2, 4, 6, 8), 5 -> Set(0, 5), 6 -> Set(0, 2, 4, 6, 8),
    7 -> Set(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 8 -> Set(0, 2, 4, 6, 8), 9 -> Set(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

  /**
   * 执行用时：424 ms, 在所有 Scala 提交中击败了 75.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：298 / 298
   *
   * @param num
   * @param k
   * @return
   */
  def minimumNumbers(num: Int, k: Int): Int = {
    if (num == 0) return 0
    num % 10 match {
      case 0 =>
        if (k == 0) 1
        else if (k == 5 && num >= 10) 2
        else if (k % 2 == 0 && num >= k * 5) 5
        else if (k % 2 == 1 && num >= k * 10) 10
        else -1
      case 5 =>
        if (k == 5) 1
        else if (k % 2 == 1 && num >= k * 5) 5
        else -1
      case odd if odd % 2 == 1 =>
        if (k % 2 == 0 || k == 5) -1
        else {
          var test = 1
          while (k * test <= num && (k * test) % 10 != odd) {
            test += 1
          }
          if (k * test > num) -1 else test
        }
      case even =>
        if (k == 0) -1
        else {
          var test = 1
          while (k * test <= num && (k * test) % 10 != even) {
            test += 1
          }
          if (k * test > num) -1 else test
        }
    }
  }
}
