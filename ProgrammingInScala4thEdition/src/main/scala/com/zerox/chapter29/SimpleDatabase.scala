package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:21
 * @note
 * 29.2 食谱应用程序
 * 29.3 抽象
 * 29.4 将模块拆分成特质
 */
object SimpleDatabase extends Database {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def allRecipes: List[Recipe] = List(FruitSalad)

//  case class FoodCategory(name: String, foods: List[Food])

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar)))

  def allCategories = categories
}

object SimpleDatabase2 extends Database with SimpleFoods with SimpleRecipes
