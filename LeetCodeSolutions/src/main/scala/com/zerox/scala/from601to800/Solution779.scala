package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/10/20 9:58
 * @note
 * 779. 第K个语法符号 | 难度：中等
 */
object Solution779 {
  /**
   * 时间 404 ms 击败 100%
   * 内存 51 MB 击败 100%
   *
   * @param n
   * @param k
   * @return
   */
  def kthGrammar(n: Int, k: Int): Int = {
    if (n == 1 && k == 1) 0
    else next(kthGrammar(n - 1, (k - 1) / 2 + 1), (k - 1) % 2)
  }

  private def next(pre: Int, idx: Int): Int = if ((pre ^ idx) == 1) 1 else 0
}
