package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/7/29 9:45
 * @note
 * 593. 有效的正方形 | 难度：中等 | 标签：几何、数学
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 *
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 *
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 *
 * 示例 1:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 *
 * 示例 2:
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 *
 * 示例 3:
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *
 * 提示:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution593 {
  /**
   * 执行用时：492 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：253 / 253
   *
   * @param p1
   * @param p2
   * @param p3
   * @param p4
   * @return
   */
  def validSquare(p1: Array[Int], p2: Array[Int], p3: Array[Int], p4: Array[Int]): Boolean = {
    val set = Set(distanceSquare(p1, p2), distanceSquare(p1, p3), distanceSquare(p1, p4),
      distanceSquare(p2, p3), distanceSquare(p2, p4), distanceSquare(p3, p4))
    set.size == 2 && set.max == 2 * set.min
  }

  private def distanceSquare(p1: Array[Int], p2: Array[Int]) = {
    (p1(0) - p2(0)).toLong * (p1(0) - p2(0)) + (p1(1) - p2(1)).toLong * (p1(1) - p2(1))
  }
}
