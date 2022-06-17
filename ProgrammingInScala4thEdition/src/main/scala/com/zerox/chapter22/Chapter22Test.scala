package com.zerox.chapter22

/**
 * @author zhuxi
 * @since 2022/6/17 10:13
 * @note
 * 第 22 章 实现列表
 */
object Chapter22Test {

  /**
   * 22.2 ListBuffer 类
   */
  def test22_2(): Unit = {
    val xs = List(1, 2, 3)
    import scala.collection.mutable.ListBuffer
    val buf = new ListBuffer[Int]
    for (x <- xs) buf += x + 1
    println(buf.toList)
  }

  def main(args: Array[String]): Unit = {
    test22_2()
  }
}
