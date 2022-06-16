package com.zerox.chapter21

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

/**
 * @author zhuxi
 * @since 2022/6/16 18:16
 * @note
 */
object Chapter21Test {
  /**
   * 21.1 隐式转换
   */
  def test21_1(): Unit = {
    implicit def function2ActionListener(f: ActionEvent => Unit) =
      new ActionListener {
        def actionPerformed(event: ActionEvent) = f(event)
      }

    val button = new JButton
    button.addActionListener((_: ActionEvent) => println("pressed!"))
    // 这段代码之所以可行，编译器首先会照原样编译，不过会遇到一个类型错误。在放弃之前，它会查找一个能修复该问题的隐式转换。
    // 在本例中，编译器找到了 function2ActionListener。它会尝试这个隐式转换，发现可行，就继续下去。
    // 编译器在这里工作很卖力，这样开发者就可以多忽略一个烦琐的细节。动作监听器？动作事件函数？都行：哪个更方便就选哪个。
    button.doClick()
  }

  /**
   * 21.3 隐式转换到一个预期的类型
   */
  def test21_3(): Unit = {
    implicit def doubleToInt(x: Double) = x.toInt

    val i: Int = 3.5
    println(i)
  }

  /**
   * 21.4 转换接收端
   */
  def test21_4(): Unit = {
    case class Rectangle(width: Int, height: Int)

    /**
     * 上述代码以通常的方式定义了一个 RectangleMaker 类。不仅如此，它还自动生成了如下转换：
     * implicit def RectangleMaker(width: Int) = new RectangleMaker(width)
     */
    implicit class RectangleMaker(width: Int) {
      def x(height: Int) = Rectangle(width, height)
    }

    val myRectangle = 3 x 4
    println(myRectangle)
    // 给那些喜欢冒险的朋友提个醒：你可能会觉得任何类定义前面都可以放 implicit。
    // 并非如此，隐式类不能是样例类，并且其构造方法必须有且仅有一个参数。不仅如此，隐式类必须存在于另一个对象、类或特质里面。
    // 在实际使用中，只要是用隐式类作为富包装类来给某个已有的类添加方法，这些限制应该都不是问题。
  }

  /**
   * 21.5 隐式参数
   */
  def test21_5(): Unit = {
    class PreferredPrompt(val preference: String)

    object Greeter {
      def greet(name: String)(implicit prompt: PreferredPrompt) = {
        println("welcome, " + name + ". The system is ready.")
        println(prompt.preference)
      }
    }
    val bobsPrompt = new PreferredPrompt("relax> ")
    Greeter.greet("Bob")(bobsPrompt)
    // 要让编译器隐式地帮你填充这个参数，必须首先定义这样一个符合预期类型的变量，在本例中这个类型是 PreferredPrompt。
    object JoesPrefs {
      implicit val prompt = new PreferredPrompt("Yes, Master> ")
    }
    import JoesPrefs._
    Greeter.greet("Joe")

    // 注意 implicit 关键字是应用到整个参数列表而不是单个参数的。
    class PreferredDrink(val preference: String)
    object Greeter2 {
      def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) = {
        println("welcome, " + name + ". The system is ready.")
        println("But while you work, why not enjoy a cup of " + drink.preference + "?")
        println(prompt.preference)
      }
    }
    object JoesPrefs2 {
      /// 前面 JoesPrefs 已经引入了隐式参数 prompt，再引入一个同类型的话，会冲突
      // implicit val prompt2 = new PreferredPrompt("Yes, Master> ")
      implicit val drink = new PreferredDrink("tea")
    }
    import JoesPrefs2._
    Greeter2.greet("Joe")(prompt, drink)
    Greeter2.greet("Joe")

    def maxListOrdering[T](elements: List[T])(ordering: Ordering[T]): T =
      elements match {
        case List() => throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxListOrdering(rest)(ordering)
          if (ordering.gt(x, maxRest)) x
          else maxRest
      }

    /**
     * maxListImpParm 函数是一个隐式参数用来提供关于在更靠前的参数列表中已经显式提到的类型的更多信息的例子。
     * 确切地说，类型为 Ordering[T] 的隐式参数 ordering 提供了更多关于类型 T 的信息（在本例中是如何对 T 排序的）。
     * 类型 T 在 elements 参数的类型 List[T] 中提到过，这是一个更靠前的参数列表中的参数。
     * 由于在任何 maxListImpParm 调用中 elements 都必须显式地给出，编译器在编译时就会知道 T 是什么，
     * 因此就可以确定类型为 Ordering[T] 的隐式定义是否可用。如果可用，它就可以隐式地作为 ordering 传入第二个参数列表。
     *
     * @param elements
     * @param ordering
     * @tparam T
     * @return
     */
    def maxListOrderingParm[T](elements: List[T])(implicit ordering: Ordering[T]): T =
      elements match {
        case List() => throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxListOrderingParm(rest)(ordering)
          if (ordering.gt(x, maxRest)) x
          else maxRest
      }

    println(maxListOrderingParm(List(1, 5, 10, 3)))
    println(maxListOrderingParm(List(1.5, 5.2, 10.7, 3.14159)))
    println(maxListOrderingParm(List("one", "two", "three")))
  }

  def test21_6(): Unit = {

    // 直观地讲，可以把上下文界定想象成对类型参数做某种描述。如果写下 [T <: Ordered[T]]，实际上是在说，T 是一个 Ordered[T]。
    // 相对而言，如果写的是[T : Ordering]，那么并没有说 T 是什么，而是说 T 带有某种形式的排序。从这个角度出发，上下文界定是很有用的。
    // 它允许我们的代码“要求”某个类型支持排序（或者关于这个类型的任何其他性质），但并不需要更改那个类型的定义。
    def maxListOrderingParm[T: Ordering](elements: List[T]): T =
      elements match {
        case List() => throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxListOrderingParm(rest) // 这里会隐式添加(ordering)
          if (implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }

    println(maxListOrderingParm(List(1, 5, 10, 3)))
    println(maxListOrderingParm(List(1.5, 5.2, 10.7, 3.14159)))
    println(maxListOrderingParm(List("one", "two", "three")))
  }

  def main(args: Array[String]): Unit = {
    test21_1()
    println("-------------")
    test21_3()
    println("-------------")
    test21_4()
    println("-------------")
    test21_5()
    println("-------------")
    test21_6()
  }
}
