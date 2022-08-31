package com.zerox.scala.from1001to1200

/**
 * @author zhuxi
 * @since 2022/8/31 10:05
 * @note
 * 1105. 填充书架 | 难度：中等 | 标签：数组、动态规划
 * 给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。你也会得到一个整数 shelfWidth 。
 *
 * 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
 *
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 *
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 *
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 *
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 *
 * 示例 1：
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 *
 * 示例 2:
 * 输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * 输出: 4
 *
 * 提示：
 * 1 <= books.length <= 1000
 * 1 <= thicknessi <= shelfWidth <= 1000
 * 1 <= heighti <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/filling-bookcase-shelves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1105 {
  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：20 / 20
   *
   * @param books
   * @param shelfWidth
   * @return
   */
  def minHeightShelves(books: Array[Array[Int]], shelfWidth: Int): Int = {
    val n = books.length
    // dp(i) 表示以第 i-1 本书（从第 0 本算起）结尾的前 i 本书叠成的最小高度
    val dp = new Array[Int](n + 1)
    dp(1) = books(0)(1)
    for (i <- 2 to n) {
      dp(i) = books(i - 1)(1) + dp(i - 1)
      var w = books(i - 1)(0)
      var h = books(i - 1)(1)
      var break = false
      for (j <- i - 1 until 0 by -1 if !break) {
        w += books(j - 1)(0)
        if (w > shelfWidth) break = true
        else {
          h = math.max(h, books(j - 1)(1))
          dp(i) = math.min(dp(i), dp(j - 1) + h)
        }
      }
    }
    dp(n)
  }
}
