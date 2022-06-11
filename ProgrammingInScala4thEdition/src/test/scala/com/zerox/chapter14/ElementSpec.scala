package com.zerox.chapter14

import com.zerox.chapter10.Element.elem
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 14:21
 * @Description: 14.4 作为规格说明的测试
 *               在 FlatSpec 中，我们以规格子句（specifier clause）的形式编写测试。
 *               先写下以字符串表示的要测试的主体（subject）（即示例 14.4 中的"A UniformElement"），然后是 should（must 或 can），
 *               再然后是一个描述该主体需要具备的某种行为的字符串，再接下来是 in。
 *               在 in 后面的花括号中，我们编写用于测试指定行为的代码。在后续的子句中，可以用 it 来指代最近给出的主体。
 *               当一个 FlatSpec 被执行时，它将每个规格子句作为 ScalaTest 测试运行。
 *               FlatSpec（以及 ScalaTest 的其他规格说明特质）在运行后将生成读起来像规格说明书的输出。
 *
 *               示例 14.4 还展示了 ScalaTest 的匹配器（matcher）领域特定语言（DSL）。
 *               通过混入 Matchers 特质，可以编写读上去更像自然语言的断言。
 *               ScalaTest 在其 DSL 中提供了许多匹配器，并允许你用定制的失败消息定义新的 matcher。
 *               示例 14.4 中的匹配器包括“should be”和 “an [...] should be thrownBy {...}”这样的语法。
 *               如果相比 should 你更喜欢 must，也可以选择混入 MustMatchers。
 * @ModifiedBy: zhuxi
 */
class ElementSpec extends AnyFlatSpec with Matchers {
  "A UniformElement" should "have a width equal to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.width should be(2)
  }
  it should "have a height equal to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.height should be(3)
  }
  // 自己试了一下 ignore 的用法
  ignore should "throw an IAE if passed a negative width" in {
    // 我自己加的，如果 ignore 改成 it 的话，这个测试过不去。
    pending
    an[IllegalArgumentException] should be thrownBy {
      elem('x', -2, 3)
    }
  }
}
