package com.zerox.chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:35
 * @Description: 10.9 多态和动态绑定
 * @ModifiedBy: zhuxi
 */
class UniformElement(ch: Char,
                     override val width: Int,
                     override val height: Int) extends Element {
  private val line = ch.toString * width

  def contents = Array.fill(height)(line)
}
