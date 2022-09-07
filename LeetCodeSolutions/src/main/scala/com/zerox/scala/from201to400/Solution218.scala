package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/7 9:55
 * @note
 * 218. 天际线问题 | 难度：困难 | 标签：树状数组、线段树、数组、分治、有序集合、扫描线、堆（优先队列）
 * 城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。
 *
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 *
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。
 *
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 * 示例 1：
 * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * 解释：
 * 图 A 显示输入的所有建筑物的位置和高度，
 * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 *
 * 示例 2：
 * 输入：buildings = [[0,2,3],[2,5,3]]
 * 输出：[[0,3],[5,0]]
 *
 * 提示：
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings 按 lefti 非递减排序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution218 {
  /**
   * 执行用时：744 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：40 / 40
   *
   * 参考题解做的。将题目化简成找水平线的左端点还是很妙的
   *
   * @param buildings
   * @return
   */
  def getSkyline(buildings: Array[Array[Int]]): List[List[Int]] = {
    val sort = buildings.flatMap(b => Array(Array(b(0), b(2), -1), Array(b(1), b(2), 1))).sortWith((a1, a2) => {
      // 对于某个横坐标而言，可能会同时出现多个点，应当按照如下规则进行处理：
      // 1. 优先处理左端点，再处理右端点
      // 2. 如果同样都是左端点，则按照高度「从大到小」进行处理（将高度增加到优先队列中）
      // 3. 如果同样都是右端点，则按照高度「从小到大」进行处理（将高度从优先队列中删掉）
      if (a1(0) != a2(0)) a1(0) < a2(0)
      else if (a1(2) != a2(2)) a1(2) < a2(2)
      else if (a1(2) == -1) a2(1) < a1(1)
      else a1(1) < a2(1)
    })
    import scala.collection.mutable
    val map = new mutable.HashMap[Int, Int]
    // 最大堆
    val pq = new mutable.PriorityQueue[Int]()
    var prev = 0
    pq.enqueue(prev)
    val res = new mutable.ListBuffer[List[Int]]
    for (Array(x, y, flag) <- sort) {
      if (flag == -1) pq.enqueue(y)
      else map.put(y, map.getOrElse(y, 0) + 1)
      var break = false
      while (pq.nonEmpty && !break) {
        val peek = pq.head
        if (map.contains(peek)) {
          if (map(peek) == 1) map.remove(peek)
          else map(peek) -= 1
          pq.dequeue()
        } else break = true
      }
      val cur = pq.head
      if (cur != prev) {
        res += List(x, cur)
        prev = cur
      }
    }
    res.toList
  }
}
