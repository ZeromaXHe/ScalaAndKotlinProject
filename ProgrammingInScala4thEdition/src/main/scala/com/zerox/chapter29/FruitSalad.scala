package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:03
 * @note
 * 29.2 食谱应用程序
 */
object Apple extends Food("Apple")

object Orange extends Food("Orange")

object Cream extends Food("Cream")

object Sugar extends Food("Sugar")

object FruitSalad extends Recipe(
  "fruit salad",
  List(Apple, Orange, Cream, Sugar),
  "Stir it all together"
)