package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 18:35
 * @note
 * 19.7 对象私有数据
 * 你可能会怀疑这段代码是否能通过 Scala 的类型检查。毕竟队列现在包含了两个协变的参数类型T的可被重新赋值的字段。
 * 这不是违背了型变规则吗？的确有这个嫌疑，不过 leading 和 trailing 带上了一个 private[this] 的修饰符，因而是对象私有的。
 * Scala 的型变检查对于对象私有定义有一个特殊规则。在检查带有 + 或 - 的类型参数只应出现在相同型变归类的位点时，会忽略掉对象私有的定义。
 */
class Queue3[+T] private(private[this] var leading: List[T],
                         private[this] var trailing: List[T]) {
  private def mirror() =
    if (leading.isEmpty) {
      while (trailing.nonEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: Queue3[T] = {
    mirror()
    new Queue3(leading.tail, trailing)
  }

  def enqueue[U >: T](x: U) = new Queue3[U](leading, x :: trailing)
}
