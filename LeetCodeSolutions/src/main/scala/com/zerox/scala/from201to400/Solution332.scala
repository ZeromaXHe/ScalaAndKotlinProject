package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/9 17:21
 * @note
 * 332. 重新安排行程 | 难度：困难 | 标签：深度优先搜索、图、欧拉回路
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 *
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 *
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 *
 * 示例 1：
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 *
 * 示例 2：
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 *
 * 提示：
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi 和 toi 由大写英文字母组成
 * fromi != toi
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reconstruct-itinerary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution332 {
  /**
   * 执行用时：568 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：80 / 80
   *
   * 参考题解做的
   *
   * @param tickets
   * @return
   */
  def findItinerary(tickets: List[List[String]]): List[String] = {
    import scala.collection.mutable
    val map = new mutable.HashMap[String, mutable.PriorityQueue[String]]
    for (ticket <- tickets) {
      if (!map.contains(ticket.head)) {
        map(ticket.head) = new mutable.PriorityQueue[String]()(Ordering.String.reverse)
      }
      map(ticket.head).addOne(ticket(1))
    }
    val res = new mutable.ListBuffer[String]

    def dfs(cur: String): Unit = {
      while (map.contains(cur)) {
        val deq = map(cur).dequeue()
        if (map(cur).isEmpty) map.remove(cur)
        dfs(deq)
      }
      res.addOne(cur)
    }

    dfs("JFK")
    res.reverse.toList
  }
}
