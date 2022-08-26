package com.zerox.scala.from1801to2000

/**
 * @author zhuxi
 * @since 2022/8/26 18:18
 * @note
 * 1927. 求和游戏 | 难度：中等 | 标签：贪心、数学、博弈
 * Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手 。
 *
 * 给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：
 *
 * 选择一个下标 i 满足 num[i] == '?' 。
 * 将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。
 * 当 num 中没有 '?' 时，游戏结束。
 *
 * Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。
 *
 * 比方说，游戏结束时 num = "243801" ，那么 Bob 获胜，因为 2+4+3 = 8+0+1 。如果游戏结束时 num = "243803" ，那么 Alice 获胜，因为 2+4+3 != 8+0+3 。
 * 在 Alice 和 Bob 都采取 最优 策略的前提下，如果 Alice 获胜，请返回 true ，如果 Bob 获胜，请返回 false 。
 *
 * 示例 1：
 * 输入：num = "5023"
 * 输出：false
 * 解释：num 中没有 '?' ，没法进行任何操作。
 * 前一半的和等于后一半的和：5 + 0 = 2 + 3 。
 *
 * 示例 2：
 * 输入：num = "25??"
 * 输出：true
 * 解释：Alice 可以将两个 '?' 中的一个替换为 '9' ，Bob 无论如何都无法使前一半的和等于后一半的和。
 *
 * 示例 3：
 * 输入：num = "?3295???"
 * 输出：false
 * 解释：Bob 总是能赢。一种可能的结果是：
 * - Alice 将第一个 '?' 用 '9' 替换。num = "93295???" 。
 * - Bob 将后面一半中的一个 '?' 替换为 '9' 。num = "932959??" 。
 * - Alice 将后面一半中的一个 '?' 替换为 '2' 。num = "9329592?" 。
 * - Bob 将后面一半中最后一个 '?' 替换为 '7' 。num = "93295927" 。
 * Bob 获胜，因为 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7 。
 *
 * 提示：
 * 2 <= num.length <= 105
 * num.length 是 偶数 。
 * num 只包含数字字符和 '?' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1927 {
  def main(args: Array[String]): Unit = {
    println(sumGame("?3295???"))
  }

  /**
   * 执行用时：560 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：95 / 95
   *
   * @param num
   * @return
   */
  def sumGame(num: String): Boolean = {
    var preCount = 0
    var preSum = 0
    for (i <- 0 until num.length / 2) {
      if (num(i) == '?') preCount += 1
      else preSum += num(i) - '0'
    }
    var sufCount = 0
    var sufSum = 0
    for (i <- num.length / 2 until num.length) {
      if (num(i) == '?') sufCount += 1
      else sufSum += num(i) - '0'
    }
    sumGame(preCount, preSum, sufCount, sufSum)
  }

  private def sumGame(lessCount: Int, noChangeSum: Int, moreCount: Int, changeSum: Int): Boolean = {
    if (lessCount > moreCount) return sumGame(moreCount, changeSum, lessCount, noChangeSum)
    if (lessCount == moreCount) return noChangeSum != changeSum
    if (changeSum >= noChangeSum || (moreCount - lessCount) % 2 == 1) return true
    val variation = (moreCount - lessCount) / 2 * 9 - 9
    changeSum + variation != noChangeSum - 9
  }
}
