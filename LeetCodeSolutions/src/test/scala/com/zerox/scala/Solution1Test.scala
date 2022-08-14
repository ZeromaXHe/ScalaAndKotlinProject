package com.zerox.scala

import com.zerox.scala.from1to200.Solution1
import org.scalatest.funsuite.AnyFunSuite

/**
 * @Author: zhuxi
 * @Time: 2022/6/14 13:41
 * @Description:
 * @ModifiedBy: zhuxi
 */
class Solution1Test extends AnyFunSuite {
  test("solution1Test") {
    assertResult(Array(0, 1)) {
      Solution1.twoSum(Array(2, 7, 11, 15), 9)
    }
  }
}
