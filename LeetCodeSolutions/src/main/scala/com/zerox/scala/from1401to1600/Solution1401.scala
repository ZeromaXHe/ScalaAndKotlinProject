package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/4 11:04
 * @note
 * 1401. 圆和矩形是否有重叠 | 难度：中等 | 标签：几何、数学
 * 给你一个以 (radius, x_center, y_center) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2)，其中 (x1, y1) 是矩形左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果圆和矩形有重叠的部分，请你返回 True ，否则返回 False 。
 *
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 *
 * 示例 1：
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形有公共点 (1,0)
 *
 * 示例 2：
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 *
 * 示例 3：
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
 * 输出：true
 *
 * 示例 4：
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 *
 * 提示：
 * 1 <= radius <= 2000
 * -10^4 <= x_center, y_center, x1, y1, x2, y2 <= 10^4
 * x1 < x2
 * y1 < y2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/circle-and-rectangle-overlapping
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1401 {
  /**
   * 执行用时：436 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：90 / 90
   *
   * @param radius
   * @param xCenter
   * @param yCenter
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   * @return
   */
  def checkOverlap(radius: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (xCenter >= x1 - radius && xCenter <= x2 + radius && yCenter >= y1 && yCenter <= y2) ||
      (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 - radius && yCenter <= y2 + radius) ||
      ((xCenter - x1) * (xCenter - x1) + (yCenter - y1) * (yCenter - y1) <= radius * radius) ||
      ((xCenter - x1) * (xCenter - x1) + (yCenter - y2) * (yCenter - y2) <= radius * radius) ||
      ((xCenter - x2) * (xCenter - x2) + (yCenter - y1) * (yCenter - y1) <= radius * radius) ||
      ((xCenter - x2) * (xCenter - x2) + (yCenter - y2) * (yCenter - y2) <= radius * radius)
  }

  /**
   * 执行用时：408 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：90 / 90
   *
   * @param radius
   * @param xCenter
   * @param yCenter
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   * @return
   */
  def checkOverlap_simple(radius: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    val dx =
      if (x1 > xCenter) x1 - xCenter
      else if (x2 < xCenter) xCenter - x2
      else 0
    val dy =
      if (y1 > yCenter) y1 - yCenter
      else if (y2 < yCenter) yCenter - y2
      else 0

    dx * dx + dy * dy <= radius * radius
  }

  /**
   * 执行用时：396 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：90 / 90
   *
   * @param radius
   * @param xCenter
   * @param yCenter
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   * @return
   */
  def checkOverlap_minmax(radius: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    val dx = xCenter - math.min(math.max(x1, xCenter), x2)
    val dy = yCenter - math.min(math.max(y1, yCenter), y2)
    dx * dx + dy * dy <= radius * radius
  }
}
