package com.zerox.chapter29

/**
 * @author zhuxi
 * @since 2022/6/20 15:01
 * @note
 * 29.2 食谱应用程序
 */
class Recipe(val name: String,
             val ingredients: List[Food],
             val instructions: String) {
  override def toString: String = name
}
