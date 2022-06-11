package com.zerox.chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:49
 * @Description: 10.11 使用组合和继承
 * @ModifiedBy: zhuxi
 */
class LineElement(s: String) extends Element {
  val contents = Array(s)

  override def width: Int = s.length

  override def height: Int = 1
}
