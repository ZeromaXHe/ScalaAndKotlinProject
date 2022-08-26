package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/8/26 13:59
 * @note
 * 973. 最接近原点的 K 个点 | 难度：中等 | 标签：几何、数组、数学、分治、快速选择、排序、堆（优先队列）
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。
 *
 * 这里，平面上两点之间的距离是 欧几里德距离（ √(x1 - x2)2 + (y1 - y2)2 ）。
 *
 * 你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案 确保 是 唯一 的。
 *
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], k = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], k = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 * 提示：
 * 1 <= k <= points.length <= 104
 * -104 < xi, yi < 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution973 {
  /**
   * 执行用时：1108 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：66.2 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：87 / 87
   *
   * @param points
   * @param k
   * @return
   */
  def kClosest(points: Array[Array[Int]], k: Int): Array[Array[Int]] = {
    // 大顶堆
    val queue = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(
      Ordering.by[(Int, Int), Int](t => t._1 * t._1 + t._2 * t._2))
    for (Array(x, y) <- points) {
      if (queue.size == k) {
        if (queue.head._1 * queue.head._1 + queue.head._2 * queue.head._2 > x * x + y * y) {
          queue.dequeue()
          queue.enqueue((x, y))
        }
      } else {
        queue.enqueue((x, y))
      }
    }
    (for (_ <- 0 until k) yield {
      val deq = queue.dequeue()
      Array(deq._1, deq._2)
    }).toArray
  }
}
