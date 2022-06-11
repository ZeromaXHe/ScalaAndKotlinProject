package com.zerox.chapter14

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 14:31
 * @Description: 14.4 作为规格说明的测试
 *               BDD的一个重要思想是测试可以在那些决定软件系统应该做什么的人、那些实现软件的人和那些判定软件是否完成并正常工作的人之间架起一道沟通的桥梁。
 *               虽然 ScalaTest 和 specs2 的任何一种风格都可以这样来用，但是 ScalaTest 的 AnyFeatureSpec 是专门为此设计的。
 *
 *               AnyFeatureSpec 的设计目的是引导关于软件需求的对话：必须指明具体的功能（feature），然后用场景（scenario）来描述这些功能。
 *               Given、When、Then方法（由 GivenWhenThen 特质提供）能帮助我们将对话聚焦在每个独立场景的具体细节上。
 *               最后的 pending 调用表明测试和实际的行为都还没有实现——这里只是规格说明。
 *               一旦所有的测试和给定的行为都实现了，这些测试就会通过，我们就可以说需求已经满足。
 * @ModifiedBy: zhuxi
 */
class TVSetSpec extends AnyFeatureSpec with GivenWhenThen {
  // 小写的 feature 和 scenario 已经标记为 @deprecated
  Feature("TV power button") {
    Scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      When("the power button is pressed")
      Then("the TV should switch on")
      pending
    }
  }
}
