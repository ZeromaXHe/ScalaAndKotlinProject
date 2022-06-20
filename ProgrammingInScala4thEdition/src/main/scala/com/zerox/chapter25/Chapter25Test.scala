package com.zerox.chapter25

/**
 * @author zhuxi
 * @since 2022/6/20 10:01
 * @note
 * 第 25 章 Scala 集合架构
 */
object Chapter25Test {

  /**
   * 25.2 集成新的集合
   */
  def test25_2(): Unit = {
    val res0 = new Capped1(capacity = 4)
    println(res0)
    val res1 = res0 :+ 1 :+ 2 :+ 3
    println(res1)
    println(res1.length)
    println(res1.lastOption)
    val res4 = res1 :+ 4 :+ 5 :+ 6
    // 通过 res4 可以看到，当你对 Capped1 追加超过 4 个元素的时候，第一个元素被丢弃。
    // 这些操作跟我们预期的基本一致，除了最后一个：调用 take 以后，得到了一个 List，而不是 Capped1。
    // 这是因为 Capped1 扩展自 Iterable，继承了它的 take 方法，而 Iterable 的 take 方法返回另一个 Iterable，这个实现默认使用了 List。
    // 这就是为什么在前面交互会话的最后一步得到了一个 List。
    println(res4)
    println(res4 take 3)

    // IterableOps 和 IterableFactory 是 2.13 引入的
    // 所以限长集合第二版 Capped2、Capped2Factory 和最终版 Capped、CappedFactory 在 2.11.12 无法实现

    // 这一章 Ops 结尾的类基本都是 2.13 引入的
  }

  def main(args: Array[String]): Unit = {
    test25_2()
  }
}
