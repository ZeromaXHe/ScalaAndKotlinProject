package com.zerox.chapter25

/**
 * @author zhuxi
 * @since 2022/6/20 10:30
 * @note
 * 25.2 集成新的集合
 */
abstract class Base

case object A extends Base

case object U extends Base

case object G extends Base

case object C extends Base

object Base {
  val fromInt: Int => Base = Array(A, U, G, C)
  val toInt: Base => Int = Map(A -> 0, U -> 1, G -> 2, C -> 3)
}