package com.zerox.chapter10

import com.zerox.chapter10.Element.elem

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 14:08
 * @Description: 10.15 放在一起
 * @ModifiedBy: zhuxi
 */
object Spiral {
  val space = elem(" ")
  val corner = elem("+")

  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      elem("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)

      def verticalBar = elem('|', 1, sp.height)

      def horizontalBar = elem('-', sp.width, 1)

      if (direction == 0)
        (corner beside horizontalBar) above (sp beside space)
      else if(direction == 1)
        (sp above space) beside (corner above verticalBar)
      else if(direction == 2)
        (space beside sp) above (horizontalBar beside corner)
      else
        (verticalBar above corner) beside (space above sp)
    }
  }

  def main(args: Array[String]): Unit = {
    val nSides = "10".toInt
    println(spiral(nSides, 0))
  }
}
