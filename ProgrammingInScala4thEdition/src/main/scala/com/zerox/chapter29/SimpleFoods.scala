package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:44
 * @note
 * 29.4 将模块拆分成特质
 */
trait SimpleFoods {
  object Pear extends Food("Pear")
  def allFoods = List(Apple, Pear)
  def allCategories = Nil
}
