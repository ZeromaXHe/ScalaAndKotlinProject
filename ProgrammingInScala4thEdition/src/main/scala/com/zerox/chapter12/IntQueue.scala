package com.zerox.chapter12

import scala.collection.mutable.ArrayBuffer

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 16:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get(): Int = buf.remove(0)

  def put(x: Int): Unit = buf += x
}

/**
 * Doubling 特质有两个好玩儿的地方。
 * 首先它声明了一个超类 IntQueue。这个声明意味着这个特质只能被混入同样继承自 IntQueue 的类。
 * 因此，可以将 Doubling 混入 BasicIntQueue，但不能将它混入 Rational。
 * 第二个好玩儿的地方是该特质有在一个声明为抽象的方法里做了一个 super 调用。
 * 对于普通的类而言这样的调用是非法的，因为它们在运行时必定会失败。不过对于特质来说，这样的调用实际上可以成功。
 * 由于特质中的 super 调用是动态绑定的，只要在给出了该方法具体定义的特质或类之后混入，Doubling 特质里的 super 调用就可以正常工作。
 */
trait Doubling extends IntQueue {
  /**
   * 对于实现可叠加修改的特质，这样的安排通常是需要的。为了告诉编译器你是特意这样做的，必须将这样的方法标记为 abstract override。
   * 这样的修饰符组合只允许用在特质的成员上，不允许用在类的成员上，它的含义是该特质必须混入某个拥有该方法具体定义的类中。
   *
   * @param x
   */
  abstract override def put(x: Int) = {
    super.put(2 * x)
  }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = {
    super.put(x + 1)
  }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = {
    if (x >= 0) super.put(x)
  }
}