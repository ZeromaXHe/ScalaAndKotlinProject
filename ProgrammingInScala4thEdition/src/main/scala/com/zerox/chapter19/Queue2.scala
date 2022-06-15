package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 15:23
 * @note
 * 19.2 信息隐藏
 * 另一种更激进的方式是隐藏类本身，并且只暴露一个反映类的公有接口的特质。
 * 示例 19.4 的代码实现了这样一种设计。其中定义了一个 Queue 特质，声明了方法 head、tail 和 enqueue。
 * 所有这三个方法都实现在子类 QueueImpl 中，这个子类本身是对象 Queue 的一个 private 的内部类。
 * 这种做法暴露给使用方的信息跟之前一样，不过采用了不同的技巧。跟之前逐个隐藏构造方法和成员方法不同，这个版本隐藏了整个实现类。
 *
 * 19.5 下界
 */
trait Queue2[+T] {
  def head: T

  def tail: Queue2[T]

  def enqueue[U >: T](x: U): Queue2[U]
}

object Queue2 {
  def apply[T](xs: T*): Queue2[T] = new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](private val leading: List[T], private val trailing: List[T]) extends Queue2[T] {
    def mirror =
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else
        this

    def head: T = mirror.leading.head

    def tail: QueueImpl[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }

    def enqueue[U >: T](x: U) = new QueueImpl(leading, x :: trailing)
  }
}
