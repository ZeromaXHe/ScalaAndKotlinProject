package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 14:04
 * @note
 * 面试题 16.07. 最大数值 | 难度：简单 | 标签：位运算、脑筋急转弯、数学
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 *
 * 示例：
 * 输入： a = 1, b = 2
 * 输出： 2
 */
object SolutionInterview16_07 {
  def main(args: Array[String]): Unit = {
    println(maximum(2147483647, -2147483648))
  }

  /**
   * 执行用时：396 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：103 / 103
   *
   * @param a
   * @param b
   * @return
   */
  def maximum(a: Int, b: Int): Int = {
    val longDiff = a.toLong - b
    val flag = longDiff >> 63
    // math.abs 函数用到了二元运算符和大小比较
    // (flag ^ longDiff) - flag 即 math.abs(longDiff) 的一种无比较实现
    (((flag ^ longDiff) - flag + a + b) / 2).toInt
  }
}
