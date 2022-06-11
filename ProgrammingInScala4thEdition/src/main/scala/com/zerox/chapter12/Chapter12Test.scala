package com.zerox.chapter12

import com.zerox.chapter6.Rational

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 15:37
 * @Description: 第 12 章 特质
 * @ModifiedBy: zhuxi
 */
object Chapter12Test {
  /**
   * 12.1 特质如何工作
   */
  def test12_1(): Unit = {
    val frog = new Frog
    frog.philosophize()
    // 特质同时也定义了一个类型。以下是一个 Philosophical 被用作类型的例子
    // 这里 phil 的类型是 Philosophical，这是一个特质。因此，变量 phil 可以由任何混入了 Philosophical 的类的对象初始化。
    val phil: Philosophical = frog
    phil.philosophize()

    val phrog: Philosophical = new Frog2
    phrog.philosophize()
  }

  /**
   * 12.3 示例：矩形对象
   */
  def test12_3(): Unit = {
    val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
    println(rect.left)
    println(rect.right)
    println(rect.width)
  }

  /**
   * 12.4 Ordered 特质
   */
  def test12_4(): Unit = {
    val half = new Rational(1, 2)
    val third = new Rational(1, 3)
    println(half < third)
    println(half > third)
  }

  /**
   * 12.5 作为可叠加修改的特质
   */
  def test12_5(): Unit = {
    val queue = new BasicIntQueue
    queue.put(10)
    queue.put(20)
    println(queue.get())
    println(queue.get())

    class MyQueue extends BasicIntQueue with Doubling
    val queue2 = new MyQueue
    queue2.put(10)
    println(queue2.get())

    println("加倍队列：")
    // 注意，MyQueue 并没有定义新的代码，它只是简单地给出一个类然后混入一个特质。
    // 在这种情况下，可以在用 new 实例化的时候直接给出 “BasicIntQueue with Doubling”，而不是定义一个有名字的类。
    val queue3 = new BasicIntQueue with Doubling
    queue3.put(10)
    println(queue3.get())

    println("剔除负整数再加一的队列：")
    // 以下是一个既过滤掉负数同时还对所有数字加一的队列
    // 混入特质的顺序是重要的。确切的规则会在下一节给出，不过粗略地讲，越靠右出现的特质越先起作用。
    // 当你调用某个带有混入的类的方法时，最靠右端的特质中的方法最先被调用。
    // 如果那个方法调用 super，它将调用左侧紧挨着它的那个特质的方法，以此类推。
    // 在例中，Filtering 的 put 最先被调用，所以它首先过滤掉了那些负的整数。
    // Incrementing 的 put 排在第二，因此它做的事情就是在 Filtering 的基础上对剩下的整数加一。
    val queue4 = new BasicIntQueue with Incrementing with Filtering
    queue4.put(-1)
    queue4.put(0)
    queue4.put(1)
    println(queue4.get())
    println(queue4.get())

    println("加一再剔除负整数的队列：")
    // 如果将顺序反过来，那么结果是首先对整数加一，然后再剔除负整数：
    val queue5 = new BasicIntQueue with Filtering with Incrementing
    queue5.put(-1)
    queue5.put(0)
    queue5.put(1)
    println(queue5.get())
    println(queue5.get())
  }

  def main(args: Array[String]): Unit = {
    test12_1()
    println("------------")
    test12_3()
    println("------------")
    test12_4()
    println("------------")
    test12_5()
  }
}
