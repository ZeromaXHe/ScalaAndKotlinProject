package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:08
 * @note
 * 第 29 章 用对象实现模块化编程
 */
object Chapter29Test {

  /**
   * 29.2 食谱应用程序
   */
  def test29_2(): Unit = {
    val apple = SimpleDatabase.foodNamed("Apple").get
    println(apple)
    println(SimpleBrowser.recipesUsing(apple))
  }

  def main(args: Array[String]): Unit = {
    test29_2()
  }
}
