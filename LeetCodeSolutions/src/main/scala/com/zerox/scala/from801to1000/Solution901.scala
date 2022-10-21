package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/21 9:47
 * @note
 * 901. 股票价格跨度 | 难度：中等
 */
object Solution901 {
  /**
   * 时间 1032 ms 击败 100%
   * 内存 66.5 MB 击败 100%
   */
  class StockSpanner() {
    val stack = new scala.collection.mutable.Stack[(Int, Int)]
    var idx = 0

    def next(price: Int): Int = {
      var res = 1
      while (stack.nonEmpty && stack.head._1 <= price) {
        res = idx - stack.pop()._2
      }
      stack.push((price, idx - res))
      idx += 1
      res
    }

  }

  /**
   * Your StockSpanner object will be instantiated and called as such:
   * var obj = new StockSpanner()
   * var param_1 = obj.next(price)
   */
}
