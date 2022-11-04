package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/11/4 9:47
 * @note
 * 754. 到达终点数字 | 难度：中等
 */
object Solution754 {
  /**
   * 执行用时：428 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param target
   * @return
   */
  def reachNumber(target: Int): Int = {
    if (target < 0) return reachNumber(-target)
    var sum = 0
    var add = 1
    while (sum < target || (sum - target) % 2 == 1) {
      sum += add
      add += 1
    }
    add - 1
  }
}
