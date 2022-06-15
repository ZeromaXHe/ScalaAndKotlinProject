package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 15:44
 * @note
 * 19.6 逆变
 * 这里的 OutputChannel 被定义为以 T 逆变。因此，一个 AnyRef 的输出通道就是一个 String 的输出通道的子类。
 * 虽然看上去有违直觉，实际上是讲得通的。我们能对一个 OutputChannel[String] 做什么，唯一支持的操作是向它写一个 String。
 * 同样的操作，一个 OutputChannel[AnyRef] 也能够完成。因此可以安全地用一个 OutputChannel[AnyRef] 来替换 OutputChannel[String]。
 * 与之相对应，在需要 OutputChannel[AnyRef] 的地方用 OutputChannel[String] 替换则是不安全的。
 * 毕竟，可以向 OutputChannel[AnyRef] 传递任何对象，而 OutputChannel[String] 要求所有被写的值都是字符串。
 */
trait OutputChannel[-T] {
  def write(x: T): Unit
}
