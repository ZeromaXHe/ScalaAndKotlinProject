package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 15:15
 * @note
 * 19.1 函数式队列
 *
 * 19.2 信息隐藏
 * 在 Java 中，我们可以通过标记为 private 来隐藏构造方法。
 * 在 Scala 中，主构造方法并没有显式的定义，它是通过类参数和类定义体隐式地定义的。
 * 尽管如此，还是可以通过在参数列表前加上 private 修饰符来隐藏主构造方法。
 * 类名和参数之间的 private 修饰符表示 Queue 的构造方法是私有的：它只能从类本身及其伴生对象访问。
 * 类名 Queue 依然是公有的，因此可以把它当作类型来使用，但不能调用其构造方法。
 */
class Queue[T] private(private val leading: List[T], private val trailing: List[T]) {
  private def mirror =
    if (leading.isEmpty)
      new Queue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head

  def tail = {
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
  }

  def enqueue(x: T) = new Queue(leading, x :: trailing)
}

object Queue {
  /**
   * 注意，由于这个工厂方法的名称是 apply，使用方代码可以用诸如 Queue(1，2，3) 这样的表达式来创建队列。
   * 这个表达式会展开成 Queue.apply(1，2，3)，因为 Queue 是对象而不是函数。这样一来，Queue 在使用方看来，就像是全局定义的工厂方法一样。
   * 实际上，Scala 并没有全局可见的方法，每个方法都必须被包含在某个对象或某个类当中。
   * 不过，通过在全局对象中使用名为 apply 的方法，可以支持看上去像是全局方法的使用模式。
   *
   * @param xs
   * @tparam T
   * @return
   */
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}
