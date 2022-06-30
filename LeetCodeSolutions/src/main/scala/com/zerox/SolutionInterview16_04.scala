package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 11:41
 * @note
 * 面试题 16.04. 井字游戏 | 难度：中等 | 标签：数组、计数、矩阵
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 *
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 *
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 *
 * 提示：
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tic-tac-toe-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_04 {
  /**
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：70 / 70
   *
   * @param board
   * @return
   */
  def tictactoe(board: Array[String]): String = {
    val n = board.length
    val col = new Array[Int](n)
    val diag = new Array[Int](2)
    var space = false
    for (i <- 0 until n) {
      var row = 0
      for (j <- 0 until n) {
        val c = board(i).charAt(j)
        if (c == ' ') space = true
        else {
          val add = if (c == 'O') 1 else -1
          row += add
          col(j) += add
          if (i == j) diag(0) += add
          if (i == n - 1 - j) diag(1) += add
        }
      }
      if (row == n) return "O"
      else if (row == -n) return "X"
    }
    for (i <- 0 until n) {
      if (col(i) == n) return "O"
      else if (col(i) == -n) return "X"
    }
    if (diag(0) == n || diag(1) == n) return "O"
    else if (diag(0) == -n || diag(1) == -n) return "X"
    if (space) "Pending" else "Draw"
  }
}
