package com.zerox.chapter14

import com.zerox.chapter10.Element
import com.zerox.chapter10.Element.elem
import org.scalatest.funsuite.AnyFunSuite

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 13:55
 * @Description: 14.2 用 Scala 写测试 ~ 14.3 翔实的失败报告
 *               ScalaTest 的核心概念是套件（suite），即测试的集合。
 *               所谓的测试（test）可以是任何带有名称，可以被启动，并且要么成功，要么失败，要么被暂停，要么被取消的代码。
 *               在 ScalaTest 中，Suite 特质是核心组合单元。
 *               Suite 声明了一组“生命周期”方法，定义了运行测试的默认方式，我们也可以重写这些方法来对测试的编写和运行进行定制。
 *
 *               ScalaTest 提供了风格特质（style trait），这些特质扩展 Suite 并重写了生命周期方法来支持不同的测试风格。
 *               它还提供了混入特质（mixin trait），这些特质重写了生命周期方法来满足特定的测试需要。
 *               可以组合 Suite 的风格和混入特质来定义测试类，以及通过编写 Suite 实例来定义测试套件。
 * @ModifiedBy: zhuxi
 */
class ElementSuite extends AnyFunSuite {
  // 测试类扩展自 AnyFunSuite，这就是风格特质的一个例子。AnyFunSuite 中的“Fun”指的是函数；
  // 而“test”是定义在 AnyFunSuite 中的一个方法，该方法被 ElementSuite 的主构造方法调用。
  // 可以在圆括号中用字符串给出测试的名称，并在花括号中给出具体的测试代码。
  // 测试代码是一个以传名参数传入 test 的函数，test 将这个函数登记下来，稍后执行。
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    test14_2(ele)
    test14_3(ele)
  }

  /**
   * 用 Scala 写测试
   *
   * @param ele
   * @return
   */
  private def test14_2(ele: Element) = {
    assert(ele.width == 2)
  }

  /**
   * 14.3 翔实的失败报告
   *
   * @param ele
   * @return
   */
  private def test14_3(ele: Element) = {
    assertResult(2) {
      ele.width
    }

    //    assertThrows[IllegalArgumentException] {
    assertThrows[NegativeArraySizeException] {
      new Array(-2)
    }

    val caught =
      intercept[ArithmeticException] {
        1 / 0
      }

    assert(caught.getMessage == "/ by zero")
  }
}
