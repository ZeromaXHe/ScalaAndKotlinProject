package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/29 15:57
 * @note
 * 2267. 检查是否有合法括号字符串路径 | 难度：困难 | 标签：数组、动态规划、矩阵
 * 一个括号字符串是一个 非空 且只包含 '(' 和 ')' 的字符串。如果下面 任意 条件为 真 ，那么这个括号字符串就是 合法的 。
 *
 * 字符串是 () 。
 * 字符串可以表示为 AB（A 连接 B），A 和 B 都是合法括号序列。
 * 字符串可以表示为 (A) ，其中 A 是合法括号序列。
 * 给你一个 m x n 的括号网格图矩阵 grid 。网格图中一个 合法括号路径 是满足以下所有条件的一条路径：
 *
 * 路径开始于左上角格子 (0, 0) 。
 * 路径结束于右下角格子 (m - 1, n - 1) 。
 * 路径每次只会向 下 或者向 右 移动。
 * 路径经过的格子组成的括号字符串是 合法 的。
 * 如果网格图中存在一条 合法括号路径 ，请返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * 输出：true
 * 解释：上图展示了两条路径，它们都是合法括号字符串路径。
 * 第一条路径得到的合法字符串是 "()(())" 。
 * 第二条路径得到的合法字符串是 "((()))" 。
 * 注意可能有其他的合法括号字符串路径。
 *
 * 示例 2：
 * 输入：grid = [[")",")"],["(","("]]
 * 输出：false
 * 解释：两条可行路径分别得到 "))(" 和 ")((" 。由于它们都不是合法括号字符串，我们返回 false 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 要么是 '(' ，要么是 ')' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2267 {
  def main(args: Array[String]): Unit = {
    println(hasValidPath(Array(Array('(', ')', ')', '('), Array('(', '(', ')', ')'), Array('(', ')', ')', ')')))) // true
  }

  /**
   * 执行用时：1728 ms, 在所有 Scala 提交中击败了 66.67% 的用户
   * 内存消耗：62 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：81 / 81
   *
   * @param grid
   * @return
   */
  def hasValidPath(grid: Array[Array[Char]]): Boolean = {
    val m = grid.length
    val n = grid(0).length
    if ((m + n) % 2 == 0 || grid(0)(0) == ')' || grid(m - 1)(n - 1) == '(') false
    else {
      val dp = new Array[Set[Int]](n)
      dp(0) = Set(1)
      var j = 1
      while (j < n) {
        dp(j) = dp(j - 1).map(_ + (if (grid(0)(j) == '(') 1 else -1))
          .filter(c => c >= 0 && c <= m + n - j - 1)
        j += 1
      }
      var i = 1
      while (i < m) {
        dp(0) = dp(0).map(_ + (if (grid(i)(0) == '(') 1 else -1))
          .filter(c => c >= 0 && c <= m - i + n - 1)
        j = 1
        while (j < n) {
          dp(j) = (dp(j) ++ dp(j - 1)).map(_ + (if (grid(i)(j) == '(') 1 else -1))
            .filter(c => c >= 0 && c <= m - i + n - j - 1)
          j += 1
        }
        i += 1
      }
      dp(n - 1).nonEmpty
    }
  }
}
