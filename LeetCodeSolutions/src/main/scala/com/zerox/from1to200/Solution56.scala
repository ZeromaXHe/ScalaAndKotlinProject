package com.zerox.from1to200

/**
 * @author zhuxi
 * @since 2022/7/29 14:24
 * @note
 * 56. 合并区间 | 难度：中等 | 标签：数组、排序
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution56 {
  /**
   * 执行用时：656 ms, 在所有 Scala 提交中击败了 57.14% 的用户
   * 内存消耗：61 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：169 / 169
   *
   * @param intervals
   * @return
   */
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    val map = new java.util.TreeMap[Integer, Int]
    for (interval <- intervals) {
      var floor = map.floorKey(interval(1))
      var l = interval(0)
      var r = interval(1)
      // 弹出所有和要插入的区间有交集的区间
      while (floor != null && map.get(floor) >= l) {
        l = math.min(l, floor)
        r = math.max(r, map.get(floor))
        map.remove(floor)
        floor = map.floorKey(interval(1))
      }
      map.put(l, r)
    }
    import scala.collection.JavaConverters.mapAsScalaMapConverter
    map.asScala.toArray.map(t => Array[Int](t._1, t._2))
  }
}
