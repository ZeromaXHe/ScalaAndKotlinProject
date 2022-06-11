package com.zerox.chapter12

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 15:51
 * @Description: 12.3 示例：矩形对象
 * @ModifiedBy: zhuxi
 */
trait Rectangular {
  def topLeft: Point

  def bottomRight: Point

  def left = topLeft.x

  def right = bottomRight.x

  def width = right - left
  // 以及更多几何方法
}

class Point(val x: Int, val y: Int)

/**
 * Component 类可以混入这个特质来获取所有由 Rectangular 提供的几何查询方法
 */
abstract class Component extends Rectangular {
  // 其他方法
}

/**
 * 同理，Rectangle 自己也可以混入这个特质
 *
 * @param topLeft
 * @param bottomRight
 */
class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  // 其他方法
}