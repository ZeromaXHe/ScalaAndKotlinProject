package com.zerox

import org.scalatest.funsuite.AnyFunSuite

/**
 * @author zhuxi
 * @since 2022/6/14 18:31
 * @note
 */
class Solution2Test extends AnyFunSuite {
  test("solution2Test") {
    import com.zerox.from1to200.Solution2._
    val node3 = new ListNode(8)
    val node2 = new ListNode(0, node3)
    val node1 = new ListNode(7, node2)
    assertResult(node1) {
      val nodeL1_3 = new ListNode(3)
      val nodeL1_2 = new ListNode(4, nodeL1_3)
      val nodeL1_1 = new ListNode(2, nodeL1_2)
      val nodeL2_3 = new ListNode(4)
      val nodeL2_2 = new ListNode(6, nodeL2_3)
      val nodeL2_1 = new ListNode(5, nodeL2_2)
      addTwoNumbers(nodeL1_1, nodeL2_1)
    }
  }
}
