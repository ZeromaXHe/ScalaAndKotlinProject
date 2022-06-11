package com.zerox.chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
object Chapter10Test {
  /**
   * 10.4 扩展类
   */
  def test10_4(): Unit = {
    // ArrayElement 类从 Element 类继承了 width 和 height 这两个方法
    // 子类型（subtying）的意思是子类的值可以被用在任何需要超类的值的场合。
    val e: Element = new ArrayElement(Array("hello", "world"))
    println(e.width)
  }

  /**
   * 10.9 多态和动态绑定
   */
  def test10_9(): Unit = {
    abstract class Element {
      def demo() = {
        println("Element's implementation invoked")
      }
    }

    class ArrayElement extends Element {
      override def demo() = {
        println("ArrayElement's implementation invoked")
      }
    }

    class LineElement extends ArrayElement {
      override def demo() = {
        println("LineElement's implementation invoked")
      }
    }

    // UniformElement 继承 Element 的 demo
    class UniformElement extends Element

    val e1: Element = new ArrayElement()
    val ae: ArrayElement = new LineElement()
    val e2: Element = ae
    val e3: Element = new UniformElement()

    def invokeDemo(e: Element) = {
      e.demo()
    }

    // 打印的都是具体实现类的方法
    invokeDemo(e1)
    invokeDemo(new ArrayElement)

    invokeDemo(ae)
    invokeDemo(e2)
    invokeDemo(new LineElement)

    invokeDemo(e3)
    invokeDemo(new UniformElement)
  }

  /**
   * 10.12 实现 above、beside 和 toString
   */
  def test10_12(): Unit = {
    // 如果其中一个操作元数组比另一个长，zip 将会扔掉多余的元素。
    // 在下面的表达式中，左操作元的第三个元素 3 并没有进入结果，因为它在右操作元中并没有对应的元素。
    println((Array(1, 2, 3) zip Array("a", "b")).mkString("Array(", ", ", ")"))
  }

  def main(args: Array[String]): Unit = {
    test10_4()
    println("----------------")
    test10_9()
    println("----------------")
    test10_12()
  }
}
