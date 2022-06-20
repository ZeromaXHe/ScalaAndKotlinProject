package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:16
 * @note
 * 29.3 抽象
 * 29.4 将模块拆分成特质
 */
abstract class Database extends FoodCategories {
  def allFoods: List[Food]

  def allRecipes: List[Recipe]

  def foodNamed(name: String): Option[Food] = allFoods.find(_.name == name)
}
