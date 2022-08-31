package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/8/31 13:42
 * @note
 * 909. 蛇梯棋 | 难度：中等 | 标签：广度优先搜索、数组、矩阵
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，从左下角开始 （即，从 board[n - 1][0] 开始）每一行交替方向。
 *
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 *
 * 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 *
 * 选定目标方格 next ，目标方格的编号符合范围 [curr + 1, min(curr + 6, n2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。 
 * 当玩家到达编号 n2 的方格时，游戏结束。
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n2 的方格上没有蛇或梯子。
 *
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 *
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
 * 返回达到编号为 n2 的方格所需的最少移动次数，如果不可能，则返回 -1。
 *
 * 示例 1：
 * 输入：board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * 输出：4
 * 解释：
 * 首先，从方格 1 [第 5 行，第 0 列] 开始。
 * 先决定移动到方格 2 ，并必须爬过梯子移动到到方格 15 。
 * 然后决定移动到方格 17 [第 3 行，第 4 列]，必须爬过蛇到方格 13 。
 * 接着决定移动到方格 14 ，且必须通过梯子移动到方格 35 。
 * 最后决定移动到方格 36 , 游戏结束。
 * 可以证明需要至少 4 次移动才能到达最后一个方格，所以答案是 4 。
 *
 * 示例 2：
 * 输入：board = [[-1,-1],[-1,3]]
 * 输出：1
 *
 * 提示：
 * n == board.length == board[i].length
 * 2 <= n <= 20
 * grid[i][j] 的值是 -1 或在范围 [1, n2] 内
 * 编号为 1 和 n2 的方格上没有蛇或梯子
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/snakes-and-ladders
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution909 {
  def main(args: Array[String]): Unit = {
    println(snakesAndLadders(Array(
      Array(-1, -1, -1, -1, -1, -1),
      Array(-1, -1, -1, -1, -1, -1),
      Array(-1, -1, -1, -1, -1, -1),
      Array(-1, 35, -1, -1, 13, -1),
      Array(-1, -1, -1, -1, -1, -1),
      Array(-1, 15, -1, -1, -1, -1)))) // 4
    println(snakesAndLadders(Array(
      Array(1, 1, -1),
      Array(1, 1, 1),
      Array(-1, 1, 1)))) // -1
  }

  /**
   * 执行用时：604 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：213 / 213
   *
   * @param board
   * @return
   */
  def snakesAndLadders(board: Array[Array[Int]]): Int = {
    val n = board.length
    val queue = new scala.collection.mutable.Queue[Int]
    queue.enqueue(0)
    var res = 0
    val set = new scala.collection.mutable.HashSet[Int]
    while (queue.nonEmpty) {
      var size = queue.size
      while (size > 0) {
        val deq = queue.dequeue()
        if (deq == n * n - 1) return res
        val far = math.min(n * n - 1, deq + 6)
        var lastNotJump = -1
        for (i <- deq + 1 to far if !set.contains(i)) {
          val x = n - 1 - i / n
          val y = if ((i / n) % 2 == 0) i % n else n - 1 - i % n
          if (board(x)(y) > -1) {
            set.add(i)
            queue.enqueue(board(x)(y) - 1)
          } else {
            lastNotJump = i
          }
        }
        if (lastNotJump > -1) queue.enqueue(lastNotJump)
        size -= 1
      }
      res += 1
    }
    -1
  }
}
