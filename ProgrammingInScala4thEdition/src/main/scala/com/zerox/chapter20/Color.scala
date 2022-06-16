package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 17:19
 * @note
 * 20.9 枚举
 * Enumeration 定义了一个名为 Value 的内部类，跟这个内部类同名的不带参数的 Value 方法每次都返回这个类的全新实例。
 * 换句话说，类似 Color.Red 这样的值的类型为 Color.Value；而 Color.Value 是所有定义在 Color 对象中的值的类型。
 * 它是一个路径依赖类型，其中 Color 是路径而 Value 是依赖的类型。这当中重要的点在于这是个完完全全的新类型，不同于所有其他类型。
 */
object Color extends Enumeration {
  val Red, Green, Blue = Value
}

/**
 * 那么 Direction.Value 会不同于 Color.Value，因为这两个类型的路径部分是不同的。
 * Scala 的 Enumeration 类还提供了其他编程语言中的枚举设计的许多其他功能特性。可以用另一个重载的 Value 方法来给枚举值关联特定的名称
 */
object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}
