package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:15
 * @note
 * 29.3 抽象
 */
abstract class Browser {
  val database: Database

  def recipesUsing(food: Food) =
    database.allRecipes.filter(recipe => recipe.ingredients.contains(food))

  def displayCategory(category: database.FoodCategory) = println(category)
}
