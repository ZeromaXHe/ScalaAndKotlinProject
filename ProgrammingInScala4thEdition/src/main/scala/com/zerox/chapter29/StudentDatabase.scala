package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:25
 * @note
 * 29.3 抽象
 */
object StudentDatabase extends Database {
  object FrozenFood extends Food("FrozenFood")

  object HeatItUp extends Recipe(
    "heat it up",
    List(FrozenFood),
    "Microwave the 'food' for 10 minutes."
  )

  def allFoods = List(FrozenFood)

  def allRecipes = List(HeatItUp)

  def allCategories = List(FoodCategory("edible", List(FrozenFood)))
}
