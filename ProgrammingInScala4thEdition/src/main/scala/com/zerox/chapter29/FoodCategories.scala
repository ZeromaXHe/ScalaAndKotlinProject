package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:30
 * @note
 * 29.4 将模块拆分成特质
 */
trait FoodCategories {
  case class FoodCategory(name: String, foods: List[Food])

  def allCategories: List[FoodCategory]
}
