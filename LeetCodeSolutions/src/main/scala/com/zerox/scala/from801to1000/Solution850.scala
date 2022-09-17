package com.zerox.scala.from801to1000

import com.zerox.scala.aider.SegmentTree

/**
 * @author zhuxi
 * @since 2022/9/16 10:00
 * @note
 * 850. 矩形面积 II | 难度：困难 | 标签：线段树、数组、有序集合、扫描线
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 *
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 *
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 *
 * 示例 1：
 * 输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示，三个矩形覆盖了总面积为6的区域。
 * 从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
 * 从(1,0)到(2,3)，三个矩形都重叠。
 *
 * 示例 2：
 * 输入：rectangles = [[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 1018 对 (109 + 7) 取模的结果， 即 49 。
 *
 * 提示：
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= xi1, yi1, xi2, yi2 <= 109
 * 矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rectangle-area-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution850 {
  /**
   * 执行用时：684 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：78 / 78
   *
   * 参考题解做的
   *
   * @param rectangles
   * @return
   */
  def rectangleArea(rectangles: Array[Array[Int]]): Int = {
    val mod = (1e9 + 7).toInt
    val set = new scala.collection.mutable.HashSet[Int]
    for (rect <- rectangles) {
      // 下边界
      set.add(rect(1))
      // 上边界
      set.add(rect(3))
    }
    val hBound = set.toList.sorted
    val m = hBound.size
    val seg = new Array[Int](m - 1)
    val sweep = (for (i <- rectangles.indices) yield {
      // 左右边界
      Array((rectangles(i)(0), i, 1), (rectangles(i)(2), i, -1))
    }).flatten.sorted.toArray

    var res = 0L
    var i = 0
    while (i < sweep.length) {
      var j = i
      while (j + 1 < sweep.length && sweep(i)._1 == sweep(j + 1)._1) j += 1
      if (j + 1 == sweep.length) return (res % mod).toInt
      for (k <- i to j) {
        val (_, idx, diff) = sweep(k)
        val left = rectangles(idx)(1)
        val right = rectangles(idx)(3)
        for (x <- 0 until m - 1 if left <= hBound(x) && hBound(x + 1) <= right) {
          seg(x) += diff
        }
      }
      var cover = 0L
      for (k <- 0 until m - 1 if seg(k) > 0) {
        cover += hBound(k + 1) - hBound(k)
      }
      res += (cover * (sweep(j + 1)._1 - sweep(j)._1))
      i = j + 1
    }
    (res % mod).toInt
  }
}
