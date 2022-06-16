package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 14:39
 * @note
 * 20.1 抽象成员概览
 * 下面这个特质声明了四种抽象成员：一个抽象类型（T）、一个抽象方法（transform）、一个 val（initial）和一个 var（current）
 */
trait Abstract {
  type T

  def transform(x: T): T

  val initial: T
  /**
   * 声明为类成员的 var 默认都带上了 getter 和 setter 方法。这一点对于抽象的 var 而言同样成立。
   * 举例来说，如果声明了名为 hour 的抽象 var，那么其实也隐式地定义了一个抽象的 getter 方法 hour，和一个抽象的 setter 方法 hour_=。
   * 这里并不需要定义一个可被重新赋值的字段，这个字段自然会出现在定义这个抽象 var 的具体实现的子类当中。
   */
  var current: T
}

/**
 * Abstract 特质的具体实现需要填充每个抽象成员的定义。下面是一个提供了这些定义的例子
 */
class Conrete extends Abstract {
  /**
   * 可以把非抽象（或者说“具体”）的类型成员，比如 Concrete 类中的类型 T，当作是一种给某个类型定义新的名称，或者说别名（alias）的方式。
   * 拿 Concrete 类来说，我们给类型 String 设置了一个别名 T。这样一来，在 Concrete 类中任何地方出现了 T，它的含义都是 String。
   * 这包括 transform 的参数和结果类型、initial 和 current 等，它们都在超特质 Abstract 的声明中提到了 T。
   * 因此，当 Concrete 类实现这些方法时，这些 T 都被解读为 String。
   */
  type T = String

  def transform(x: String) = x + x

  /**
   * 抽象 val 限制了它的合法实现：任何实现都必须是一个 val 定义；而不能是 var 或 def。
   * 从另一方面讲，抽象方法声明则可以用具体的方法定义或具体的 val 定义实现。
   */
  val initial = "hi"
  var current = initial
}
