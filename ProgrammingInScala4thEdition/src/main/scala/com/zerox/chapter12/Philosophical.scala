package com.zerox.chapter12

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 15:31
 * @Description: 12.1 特质如何工作
 *               特质的定义跟类定义很像，除了关键字trait。
 *               该特质名为 Philosophical。它并没有声明一个超类，因此跟类一样，有一个默认的超类 AnyRef。
 *               它定义了一个名为 philosophize 的方法，这个方法是具体的。这是个简单的特质，只是为了展示特质的工作原理。
 * @ModifiedBy: zhuxi
 */
trait Philosophical {
  def philosophize() = {
    println("I consume memory, therefore I am!")
  }
}

/**
 * 一旦特质被定义好，我们就可以用 extends 或 with 关键字将它混入到类中。
 * Scala 程序员混入（mix in）特质，而不是从特质继承，因为混入特质跟其他许多编程语言中的多重继承有重要的区别。
 * 示例 12.2 展示了一个用 extends 混入了 Philosophical 特质的类
 *
 * 可以用 extends 关键字来混入特质，在这种情况下隐式地继承了特质的超类。
 * 例如，在示例 12.2 中，Frog 类是 AnyRef 的子类（因为 AnyRef 是 Philosophical 的超类），并且混入了 Philosophical。
 * 从特质继承的方法跟从超类继承的方法用起来一样。
 */
class Frog extends Philosophical {
  override def toString: String = "green"
}

class Animal

trait HasLegs

// 如果想要将特质混入一个显式继承自某个超类的类，可以用 extends 来给出这个超类，并用 with 来混入特质。
// 由于这个新的 Frog 定义仍然混入了 Philosophical 特质，仍然可以用同一个该类型的变量使用它。
// 不过由于 Frog 重写了 Philosophical 的 philosophize 实现，当你调用这个方法时，将得到新的行为
class Frog2 extends Animal with Philosophical with HasLegs {
  override def toString: String = "green"

  override def philosophize(): Unit = {
    println("It ain't easy being " + toString + "!")
  }
}

// 至此，你可能会总结（philosophize）出特质很像是拥有具体方法的 Java 接口，不过它们能做的实际上远不止这些。
// 比方说，特质可以声明字段并保持状态。事实上，在特质定义中可以做任何在类定义中做的事，语法也完全相同，除了以下两种情况：
// 首先，特质不能有任何“类”参数（即那些传入类的主构造方法的参数）。
// 另一个类和特质的区别在于类中的 super 调用是静态绑定的，而在特质中 super 是动态绑定的。
// 如果在类中编写“super.toString”这样的代码，你会确切地知道实际调用的是哪一个实现。在你定义特质的时候并没有被定义。
// 具体是哪个实现被调用，在每次该特质被混入到某个具体的类时，都会重新判定。
// 这里 super 看上去有些奇特的行为是特质能实现可叠加修改（stackable modification）的关键，我们将在12.5节介绍这个概念。
// 解析 super 调用的规则将在12.6节给出。