package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/29 11:18
 * @note
 * 2281. 巫师的总力量和 | 难度：困难 | 标签：栈、数组、前缀和、单调栈
 * 作为国王的统治者，你有一支巫师军队听你指挥。
 *
 * 给你一个下标从 0 开始的整数数组 strength ，其中 strength[i] 表示第 i 位巫师的力量值。
 * 对于连续的一组巫师（也就是这些巫师的力量值是 strength 的 子数组），总力量 定义为以下两个值的 乘积 ：
 *
 * 巫师中 最弱 的能力值。
 * 组中所有巫师的个人力量值 之和 。
 * 请你返回 所有 巫师组的 总 力量之和。由于答案可能很大，请将答案对 109 + 7 取余 后返回。
 *
 * 子数组 是一个数组里 非空 连续子序列。
 *
 * 示例 1：
 * 输入：strength = [1,3,1,2]
 * 输出：44
 * 解释：以下是所有连续巫师组：
 * - [1,3,1,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
 * - [1,3,1,2] 中 [3] ，总力量值为 min([3]) * sum([3]) = 3 * 3 = 9
 * - [1,3,1,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
 * - [1,3,1,2] 中 [2] ，总力量值为 min([2]) * sum([2]) = 2 * 2 = 4
 * - [1,3,1,2] 中 [1,3] ，总力量值为 min([1,3]) * sum([1,3]) = 1 * 4 = 4
 * - [1,3,1,2] 中 [3,1] ，总力量值为 min([3,1]) * sum([3,1]) = 1 * 4 = 4
 * - [1,3,1,2] 中 [1,2] ，总力量值为 min([1,2]) * sum([1,2]) = 1 * 3 = 3
 * - [1,3,1,2] 中 [1,3,1] ，总力量值为 min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
 * - [1,3,1,2] 中 [3,1,2] ，总力量值为 min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
 * - [1,3,1,2] 中 [1,3,1,2] ，总力量值为 min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
 * 所有力量值之和为 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44 。
 *
 * 示例 2：
 * 输入：strength = [5,4,6]
 * 输出：213
 * 解释：以下是所有连续巫师组：
 * - [5,4,6] 中 [5] ，总力量值为 min([5]) * sum([5]) = 5 * 5 = 25
 * - [5,4,6] 中 [4] ，总力量值为 min([4]) * sum([4]) = 4 * 4 = 16
 * - [5,4,6] 中 [6] ，总力量值为 min([6]) * sum([6]) = 6 * 6 = 36
 * - [5,4,6] 中 [5,4] ，总力量值为 min([5,4]) * sum([5,4]) = 4 * 9 = 36
 * - [5,4,6] 中 [4,6] ，总力量值为 min([4,6]) * sum([4,6]) = 4 * 10 = 40
 * - [5,4,6] 中 [5,4,6] ，总力量值为 min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
 * 所有力量值之和为 25 + 16 + 36 + 36 + 40 + 60 = 213 。
 *
 * 提示：
 * 1 <= strength.length <= 105
 * 1 <= strength[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-total-strength-of-wizards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2281 {
  private final val MOD = (1e9 + 7).toInt

  /**
   * 执行用时：1144 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：84.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：82 / 82
   *
   * @param strength
   * @return
   */
  def totalStrength(strength: Array[Int]): Int = {
    val n = strength.length
    val preSumOfPreSum = new Array[Int](n + 2)
    var sum = 0
    for (i <- 0 until n) {
      sum = (strength(i) + sum) % MOD
      preSumOfPreSum(i + 2) = (preSumOfPreSum(i + 1) + sum) % MOD
    }
    // 和 2334 题有点不一样，right 是取小于等于。
    val left = new Array[Int](n)
    val right = Array.fill(n)(n)
    val stack = new scala.collection.mutable.Stack[Int]
    for (i <- 0 until n) {
      while (stack.nonEmpty && strength(stack.top) >= strength(i)) right(stack.pop()) = i
      left(i) = if (stack.isEmpty) -1 else stack.top
      stack.push(i)
    }
    var result = 0L
    for (i <- 0 until n) {
      val l = left(i) + 1
      val r = right(i) - 1
      val total = ((i - l + 1).toLong * (preSumOfPreSum(r + 2) - preSumOfPreSum(i + 1))
        - (r - i + 1).toLong * (preSumOfPreSum(i + 1) - preSumOfPreSum(l))) % MOD
      result = (result + strength(i) * total) % MOD
    }
    ((result + MOD) % MOD).toInt
  }
}
