package chapter13

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 17:02
 * @Description: 13.1 将代码放进包里
 * @ModifiedBy: zhuxi
 */
abstract class Fruit(val name: String, val color: String)

object Fruits {
  object Apple extends Fruit("apple", "red")

  object Orange extends Fruit("orange", "orange")

  object Pear extends Fruit("pear", "yellowish")

  val menu = List(Apple, Orange, Pear)
}