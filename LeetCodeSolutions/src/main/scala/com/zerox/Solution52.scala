package com.zerox

/**
 * @author ZeromaXHe
 * @since 2022/7/6 22:49
 * @note
 * 52. N皇后 II | 难度：困难 | 标签：回溯
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution52 {
  /**
   * 执行用时：1412 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：55.8 MB, 在所有 Scala 提交中击败了 66.67% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def totalNQueens(n: Int): Int = {
    (0 until n).permutations.count(_.zipWithIndex.combinations(2) forall {
      case Seq((a, b), (c, d)) => a + b != c + d && a + d != b + c
    })
  }

  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：52.2 MB, 在所有 Scala 提交中击败了 66.67% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def totalNQueens2(n: Int): Int = {
    def nQueens(k: Int): List[List[Int]] =
      if (k == 0) List(Nil)
      else nQueens(k - 1).flatMap(q => (0 until n).withFilter(c => isValid(c, q)).map(c => c :: q))

    def isValid(col: Int, queens: List[Int]) =
      (1 to queens.length).zip(queens).forall({ case (r, c) => c != col && r != (col - c).abs })

    nQueens(n).length
  }
}
