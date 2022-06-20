package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:46
 * @note
 * 29.4 将模块拆分成特质
 */
trait SimpleRecipes {
  // 不过有一种方式可以让你告诉编译器这个要求。Scala 专门提供了自身类型（self type）来应对这种情况。
  // 从技术上讲，自身类型是在类中提到 this 时，对于 this 的假定类型。从实用角度讲，自身类型指定了对于特质能够混入的具体类的要求。
  // 如果你的特质仅能用于混入另一个或几个特定的特质，那么你可以指定那些特质作为自身类型。
  this: SimpleFoods =>
  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Pear),
    "Mix it all together.")

  def allRecipes = List(FruitSalad)
}
