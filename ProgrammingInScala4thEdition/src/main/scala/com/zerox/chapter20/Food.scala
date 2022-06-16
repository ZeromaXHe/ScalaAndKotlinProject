package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 17:06
 * @note
 * 20.6 抽象类型
 */
class Food

abstract class Animal {
  type SuitableFood <: Food

  def eat(food: SuitableFood)
}

class Grass extends Food

class Cow extends Animal {
  type SuitableFood = Grass

  override def eat(food: Grass) = {}
}

/**
 * 20.8 改良类型
 * 当一个类从另一个类继承时，将前者称为另一个的名义（nominal）子类型。
 * 之所以是名义子类型，是因为每个类型都有一个名称，而这些名称被显式地声明为存在子类型关系。
 * 除此之外，Scala 还额外支持结构（structural）子类型，即只要两个类型有兼容的成员，就可以说它们之间存在子类型关系。
 * Scala 实现结构子类型的方式是改良类型（refinement type）
 */
class Pasture {
  var animals: List[Animal{type SuitableFood = Grass}] = Nil
}
