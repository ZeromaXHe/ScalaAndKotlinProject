package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/28 9:55
 * @note
 * 907. 子数组的最小值之和 | 难度：中等
 */
object Solution907 {
  /**
   * 时间 760 ms 击败 100%
   * 内存 62.6 MB 击败 100%
   *
   * @param arr
   * @return
   */
  def sumSubarrayMins(arr: Array[Int]): Int = {
    val n = arr.length
    val stack = new scala.collection.mutable.Stack[Int]
    val left = new Array[Int](n)
    val right = new Array[Int](n)
    for (i <- arr.indices) {
      while (stack.nonEmpty && arr(i) <= arr(stack.head)) stack.pop()
      left(i) = i - (if (stack.isEmpty) -1 else stack.head)
      stack.push(i)
    }
    stack.clear()
    for (i <- arr.indices.reverse) {
      while (stack.nonEmpty && arr(i) < arr(stack.head)) stack.pop()
      right(i) = (if (stack.isEmpty) n else stack.head) - i
      stack.push(i)
    }
    var res = 0L
    val MOD = (1e9 + 7).toInt
    for (i <- arr.indices) res = (res + left(i).toLong * right(i) * arr(i)) % MOD
    res.toInt
  }
}
