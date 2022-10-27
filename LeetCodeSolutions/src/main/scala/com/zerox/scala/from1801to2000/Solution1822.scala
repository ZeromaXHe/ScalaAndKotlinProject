package com.zerox.scala.from1801to2000

/**
 * @author zhuxi
 * @since 2022/10/27 9:54
 * @note
 * 1822. 数组元素积的符号 | 难度：简单
 */
object Solution1822 {
  /**
   * 时间 544 ms 击败 50%
   * 内存 53.4 MB 击败 50%
   *
   * @param nums
   * @return
   */
  def arraySign(nums: Array[Int]): Int = {
    nums.map(n => if (n == 0) 0 else n / n.abs).product
  }
}
