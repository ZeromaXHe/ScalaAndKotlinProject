package com.zerox.chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:02
 * @Description: 10.4 扩展类
 * @ModifiedBy: zhuxi
 */
class ArrayElement(conts:Array[String]) extends Element {
  // extends 子句有两个作用：它使得 ArrayElement 类从 Element 类继承（inherit）所有非私有的成员，
  // 并且它也让 ArrayElement 的类型成为 Element 类型的子类型（subtype）。
  // 由于 ArrayElement 扩展自 Element，ArrayElement 类被称作 Element 类的子类（subclass）。
  // 反过来讲，Element 是 ArrayElement 的超类（superclass）。
  // 如果你去掉 extends 子句，Scala 编译器会默认假定你的类扩展自 scala.AnyRef，这对应到 Java 平台跟 java.lang.Object 相同。
  // 因此，Element 类默认也扩展自 AnyRef 类。
  override def contents: Array[String] = conts
}
