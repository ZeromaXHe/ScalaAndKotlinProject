package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/11/2 9:47
 * @note
 * 1620. 网络信号最好的坐标 | 难度：中等
 */
object Solution1620 {
  /**
   * 执行用时：608 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：100 / 100
   *
   * @param towers
   * @param radius
   * @return
   */
  def bestCoordinate(towers: Array[Array[Int]], radius: Int): Array[Int] = {
    var xMin = towers(0)(0)
    var yMin = towers(0)(1)
    var xMax = towers(0)(0)
    var yMax = towers(0)(1)
    for (tower <- towers) {
      if (tower(0) > xMax) xMax = tower(0)
      else if (tower(0) < xMin) xMin = tower(0)
      if (tower(1) > yMax) yMax = tower(1)
      else if (tower(1) < yMin) yMin = tower(1)
    }
    val res = new Array[Int](2)
    var max = 0
    for (x <- xMin to xMax; y <- yMin to yMax) {
      var sum = 0
      for (t <- towers) {
        val d = math.sqrt((t(0) - x) * (t(0) - x) + (t(1) - y) * (t(1) - y))
        if (d <= radius) sum += (t(2) / (1 + d)).toInt
      }
      if (sum > max) {
        max = sum
        res(0) = x
        res(1) = y
      }
    }
    res
  }
}
